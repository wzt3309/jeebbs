package com.jeecms.core.bbcode;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import com.jeecms.bbs.web.StrUtils;

public class BbcodeHandler extends DefaultHandler implements InitializingBean {
	private Map<String, Bbcode> bbMap = new LinkedHashMap<String, Bbcode>();
	private Map<String, Bbcode> alwaysProcessMap = new LinkedHashMap<String, Bbcode>();
	private String tagName = "";
	private StringBuffer sb;
	private Bbcode bb;

	public BbcodeHandler() {
	}

	public String bbcode2html(String s) {
		if (StringUtils.isBlank(s)) {
			return s;
		}
		s = StrUtils.txt2htm(s);
		return processText(s);
	}

	public String processText(String s) {
		int codeIndex = s.indexOf("[code");
		int codeEndIndex = codeIndex > -1 ? s.indexOf("[/code]") : -1;

		if (codeIndex == -1 || codeEndIndex == -1 || codeEndIndex < codeIndex) {
			return bbcode2htmlExceptCodeTag(s);
		} else {
			int nextStartPos = 0;
			StringBuilder result = new StringBuilder(s.length());

			while (codeIndex > -1 && codeEndIndex > -1
					&& codeEndIndex > codeIndex) {
				codeEndIndex += "[/code]".length();
				String nonCodeResult = bbcode2htmlExceptCodeTag(s.substring(
						nextStartPos, codeIndex));
				String codeResult = parseCode(s.substring(codeIndex,
						codeEndIndex));
				result.append(nonCodeResult).append(codeResult);
				nextStartPos = codeEndIndex;
				codeIndex = s.indexOf("[code", codeEndIndex);
				codeEndIndex = codeIndex > -1 ? s.indexOf("[/code]", codeIndex)
						: -1;
			}

			if (nextStartPos > -1) {
				String nonCodeResult = bbcode2htmlExceptCodeTag(s
						.substring(nextStartPos));
				result.append(nonCodeResult);
			}
			return result.toString();
		}
	}

	private String parseCode(String text) {
		for (Iterator<Bbcode> iter = getBbList().iterator(); iter.hasNext();) {
			Bbcode bb = iter.next();
			if (bb.getTagName().startsWith("code")) {
				Matcher matcher = Pattern.compile(bb.getRegex()).matcher(text);
				StringBuffer sb = new StringBuffer(text);
				while (matcher.find()) {
					StringBuilder lang = null;
					StringBuilder contents = null;
					if ("code".equals(bb.getTagName())) {
						contents = new StringBuilder(matcher.group(1));
					} else {
						lang = new StringBuilder(matcher.group(1));
						contents = new StringBuilder(matcher.group(2));
					}
					StrUtils.replace(contents, "<br /> ", "\n");
					// XML-like tags
					StrUtils.replace(contents, "<", "&lt;");
					StrUtils.replace(contents, ">", "&gt;");
					// Note: there is no replacing for spaces and tabs as
					// we are relying on the Javascript SyntaxHighlighter
					// library
					// to do it for us
					StringBuffer replace = new StringBuffer(bb.getReplace());
					int index = replace.indexOf("$1");
					if ("code".equals(bb.getTagName())) {
						if (index > -1) {
							replace.replace(index, index + 2, contents
									.toString());
						}
						index = sb.indexOf("[code]");
					} else {
						if (index > -1) {
							replace.replace(index, index + 2, lang.toString());
						}
						index = replace.indexOf("$2");
						if (index > -1) {
							replace.replace(index, index + 2, contents
									.toString());
						}
						index = sb.indexOf("[code=");
					}
					int lastIndex = sb.indexOf("[/code]", index)
							+ "[/code]".length();

					if (lastIndex > index) {
						sb.replace(index, lastIndex, replace.toString());
					}
				}
				text = sb.toString();
			}
		}
		return text;
	}

	public String bbcode2htmlExceptCodeTag(String text) {
		if (text == null) {
			return text;
		}

		if (text.indexOf('[') > -1 && text.indexOf(']') > -1) {
			for (Iterator<Bbcode> iter = getBbList().iterator(); iter.hasNext();) {
				Bbcode bb = iter.next();
				if (!bb.getTagName().startsWith("code")) {
					text = text.replaceAll(bb.getRegex(), bb.getReplace());
				}
			}
		}

		text = parseDefaultRequiredBBCode(text);

		return text;
	}

	public String parseDefaultRequiredBBCode(String text) {
		Collection<Bbcode> list = getAlwaysProcessList();

		for (Iterator<Bbcode> iter = list.iterator(); iter.hasNext();) {
			Bbcode bb = iter.next();
			text = text.replaceAll(bb.getRegex(), bb.getReplace());
		}

		return text;
	}

	public void addBb(Bbcode bb) {
		if (bb.alwaysProcess()) {
			this.alwaysProcessMap.put(bb.getTagName(), bb);
		} else {
			this.bbMap.put(bb.getTagName(), bb);
		}
	}

	public Collection<Bbcode> getBbList() {
		return this.bbMap.values();
	}

	public Collection<Bbcode> getAlwaysProcessList() {
		return this.alwaysProcessMap.values();
	}

	public Bbcode findByName(String tagName) {
		return (Bbcode) this.bbMap.get(tagName);
	}

	public void startElement(String uri, String localName, String tag,
			Attributes attrs) {
		if (tag.equals("match")) {
			this.sb = new StringBuffer();
			this.bb = new Bbcode();

			String tagName = attrs.getValue("name");
			if (tagName != null) {
				this.bb.setTagName(tagName);
			}

			// Shall we remove the infamous quotes?
			String removeQuotes = attrs.getValue("removeQuotes");
			if (removeQuotes != null && removeQuotes.equals("true")) {
				this.bb.enableRemoveQuotes();
			}

			String alwaysProcess = attrs.getValue("alwaysProcess");
			if (alwaysProcess != null && "true".equals(alwaysProcess)) {
				this.bb.enableAlwaysProcess();
			}
		}

		this.tagName = tag;
	}

	public void endElement(String uri, String localName, String tag) {
		if (tag.equals("match")) {
			this.addBb(this.bb);
		} else if (this.tagName.equals("replace")) {
			this.bb.setReplace(this.sb.toString().trim());
			this.sb.delete(0, this.sb.length());
		} else if (this.tagName.equals("regex")) {
			this.bb.setRegex(this.sb.toString().trim());
			this.sb.delete(0, this.sb.length());
		}

		this.tagName = "";
	}

	public void characters(char ch[], int start, int length) {
		if (this.tagName.equals("replace") || this.tagName.equals("regex"))
			this.sb.append(ch, start, length);
	}

	public void error(SAXParseException exception) throws SAXException {
		throw exception;
	}

	public void afterPropertiesSet() throws Exception {
		SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
		parser.parse(configLocation.getInputStream(), this);
		handler = this;
	}

	private static BbcodeHandler handler;

	public static String toHtml(String s) {
		if (handler == null) {
			throw new RuntimeException("BbcodeHandler not prepared!");
		}
		return handler.bbcode2html(s);
	}

	private Resource configLocation;

	public Resource getConfigLocation() {
		return configLocation;
	}

	public void setConfigLocation(Resource configLocation) {
		this.configLocation = configLocation;
	}
}
