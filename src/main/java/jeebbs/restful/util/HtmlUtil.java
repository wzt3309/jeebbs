package jeebbs.restful.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ztwang on 2017/6/29 0029.
 */
public final class HtmlUtil {

    private HtmlUtil() {}
    public static Elements getElementsBySelector(String html, String selector) {
        if (StringUtils.isEmpty(html) || StringUtils.isEmpty(selector)) return null;
        Document doc = Jsoup.parse(html);
        Elements elements = doc.select(selector);
        return elements;
    }

    /**
     * 对此element子元素进行selector筛选，获取符合条件的children
     * @param element
     * @param selector
     * @return
     */
    public static Elements getChildrenBySelector(Element element, String selector) {
        if (element == null || StringUtils.isEmpty(selector)) return null;
        Elements allChildren = element.children();
        if (allChildren != null && !allChildren.isEmpty()) return allChildren.select(selector);
        return null;
    }

    public static Element getChildrenBySelector(Element element, String selector, int at) {
        Elements elements = getChildrenBySelector(element, selector);
        return (Element) get(elements, at);
    }

    /**
     * 对所有elements的子元素进行selector筛选，获取符合条件的children
     * @param elements
     * @param selector
     * @return
     */
    public static List<Elements> getChildrenBySelector(Elements elements, String selector) {
        if (elements == null || elements.isEmpty() || StringUtils.isEmpty(selector)) return null;
        List<Elements> res = new ArrayList<>();
        for (Element element: elements) {
            Elements childrenSelected = getChildrenBySelector(element, selector);
            if (childrenSelected != null && !childrenSelected.isEmpty()) res.add(elements);
        }
        return res;
    }

    public static List<String> parse(Elements elements) {
        if (elements == null || elements.isEmpty()) return null;
        List<String> parseList = new ArrayList<>(elements.size());
        for (Element element: elements) {
            String text = element.ownText();
            parseList.add(text);
        }
        return parseList;
    }

    public static String parse(Elements elements ,int at) {
        List<String> parseList = parse(elements);
        return (String) get(parseList, at);
    }

    public static List<String> parseBySelector(String html, String selector) {
        Elements elements = getElementsBySelector(html, selector);
        return parse(elements);
    }

    public static String parseBySelector(String html, String selector, int at) {
        List<String> parseList = parseBySelector(html, selector);
        return (String) get(parseList, at);
    }

    public static List<Map<String, String>> getAttrs(Elements elements) {
        if (elements == null || elements.isEmpty()) return null;
        List<Map<String, String>> attrsList = new ArrayList<>(elements.size());
        for (Element element: elements) {
            Map<String, String> dataset = element.dataset();
            if (dataset == null || dataset.isEmpty()) attrsList.add(null);
            attrsList.add(dataset);
        }
        return attrsList;
    }

    public static Map<String, String> getAttrs(Elements elements, int at) {
        List<Map<String, String>> attrsList = getAttrs(elements);
        return (Map<String, String>) get(attrsList, at);
    }

    public static List<Map<String, String>> getAttrsBySelector(String html, String selector) {
        Elements elements = getElementsBySelector(html, selector);
        return getAttrs(elements);
    }

    public static Map<String, String> getAttrsBySelector(String html, String selector, int at) {
        List<Map<String, String>> attrsList = getAttrsBySelector(html, selector);
        return (Map<String, String>) get(attrsList, at);
    }

    private static Object get(List list, int at) {
        if (list == null || list.isEmpty()) return null;
        if (at >= list.size() || at < 0) throw new IndexOutOfBoundsException(
                String.format("list length is %d but at: %d", list.size(), at));
        return list.get(at);
    }
}
