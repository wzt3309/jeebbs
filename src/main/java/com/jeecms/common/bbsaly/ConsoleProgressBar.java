package com.jeecms.common.bbsaly;

import java.text.DecimalFormat;
/**
 * 对长时进行的进程 制造进度条显示
 * 便于观察进程运行情况
 * @author wzt3309
 *
 */
public class ConsoleProgressBar {

	private long minimum = 0; // 进度条起始值
    
    private long maximum = 100; // 进度条最大值
    
    private long barLen = 100; // 进度条长度
    
    private char showChar = '='; // 用于进度条显示的字符
    
    private DecimalFormat formater = new DecimalFormat("#.##%");
    
    private String name;//使用进度条的程序名
    /**
     * 使用系统标准输出，显示字符进度条及其百分比。
     */
    public ConsoleProgressBar() {
    }
    
    /**
     * 使用系统标准输出，显示字符进度条及其百分比。
     * 
     * @param minimum 进度条起始值
     * @param maximum 进度条最大值
     * @param barLen 进度条长度
     */
    public ConsoleProgressBar(long minimum, long maximum, 
            long barLen) {
        this(minimum, maximum, barLen, '=');
    }
    
    /**
     * 使用系统标准输出，显示字符进度条及其百分比。
     * 
     * @param minimum 进度条起始值
     * @param maximum 进度条最大值
     * @param barLen 进度条长度
     * @param showChar 用于进度条显示的字符
     */
    public ConsoleProgressBar(long minimum, long maximum, 
            long barLen, char showChar) {
        this.minimum = minimum;
        this.maximum = maximum;
        this.barLen = barLen;
        this.showChar = showChar;
    }
    public ConsoleProgressBar(long minimum, long maximum, 
            long barLen, char showChar,String name) {
        this.minimum = minimum;
        this.maximum = maximum;
        this.barLen = barLen;
        this.showChar = showChar;
        this.name=name;
    }
    
    /**
     * 显示进度条。
     * 
     * @param value 当前进度。进度必须大于或等于起始点且小于等于结束点（start <= current <= end）。
     */
    public void show(long value) {
        if (value < minimum || value > maximum) {
            return;
        }
        
        reset();
        minimum = value;
        float rate = (float) (minimum*1.0 / maximum);
        long len = (long) (rate * barLen);
        draw(len, rate);
        if (minimum == maximum) {
            afterComplete();
        }
    }

    private void draw(long len, float rate) {
    	if(name!=null&&name.length()>0)
    		System.out.print(name+"：");
        for (int i = 0; i < len; i++) {
            System.out.print(showChar);
        }
        System.out.print(' ');
        System.out.print(format(rate));
    }
    
    private void reset() {
        System.out.print('\r');
    }
    
    private void afterComplete() {
        System.out.print('\n');
    }
    
    private String format(float num) {
        return formater.format(num);
    }
    
    public static void main(String[] args) throws InterruptedException {
        ConsoleProgressBar cpb = new ConsoleProgressBar(0, 100, 20, '=');
        for (int i = 1; i <= 100; i++) {
            cpb.show(i);
            Thread.sleep(100);
        }
    }

}
