import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Enumeration;
import java.util.TooManyListenersException;

import gnu.io.*;

/*
 * 创建一个类JavaSerialPort
 * 通过关键词implements实现声明
 * 自己使用Runnable和SerialPortEventListener这两个接口
 */
@SuppressWarnings("unused")
public class ComPortRead implements SerialPortEventListener {
	// 定义、声明变量
	public static String PortName;
	private CommPortIdentifier commPort;
	private SerialPort serialPort; //打开的端口
	private InputStream inputStream = null; //输入流
	public static String messageString; //给选定端口发送的字符
	static Scanner scan = new Scanner(System.in);
    
	/*
	 * listPort()方法的定义
	 * java串行端口探测方法
	 */
	@SuppressWarnings("rawtypes")
	public void listPort() {
		CommPortIdentifier cpid;
		Enumeration en = CommPortIdentifier.getPortIdentifiers();
		System.out.println("端口信息 ：" + en);
		System.out.println("可用端口列表如下：");
		while (en.hasMoreElements()) {
			cpid = (CommPortIdentifier) en.nextElement();
			if (cpid.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				System.out.println(cpid.getName() + ", "
						+ cpid.getCurrentOwner());
			}
		}
	}

	/*
	 * selectPort(String portName)方法的定义
	 * 端口选择方法
	 */
	@SuppressWarnings("rawtypes")
	public void selectPort(String portName) {
		String portname = portName;
		this.commPort = null;
		CommPortIdentifier cpid;
		Enumeration en = CommPortIdentifier.getPortIdentifiers();
		while (en.hasMoreElements()) {
			cpid = (CommPortIdentifier) en.nextElement();
			if (cpid.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				if (cpid.getName().equals(portName)) {
					this.commPort = cpid;
				}
			}
		}
		ComEventListener(portname);
	}

    public void ComEventListener(String portName){
        try {
            //获取串口、打开窗串口、获取串口的输入流\
        	String portname = portName;
            commPort = CommPortIdentifier.getPortIdentifier(portname);
            serialPort = (SerialPort) commPort.open("ComEventListener", 1000);
            inputStream = serialPort.getInputStream();

            //向串口添加事件监听对象
            serialPort.addEventListener(this);
            //设置当端口有可用数据时触发事件，此设置必不可少
            serialPort.notifyOnDataAvailable(true);
            /*设置串口初始化参数，依次是波特率，数据位，停止位和校验*/  
            serialPort.setSerialPortParams(4800, SerialPort.DATABITS_8,SerialPort.STOPBITS_1 , SerialPort.PARITY_NONE);  
        } catch (NoSuchPortException e) {
        	System.out.println("端口不存在！");
        	//e.printStackTrace();
        } catch (PortInUseException e) {
        	System.out.println("端口被占用!");
            //e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TooManyListenersException e) {
            e.printStackTrace();
        }catch (UnsupportedCommOperationException e) {  
            e.printStackTrace();  
        } 

    }

    /*
     * 重写继承的监听器方法
     * (non-Javadoc)
     * @see gnu.io.SerialPortEventListener#serialEvent(gnu.io.SerialPortEvent)
     */
	public void serialEvent(SerialPortEvent event) {
        //定义用于缓存读入数据的数组
        byte[] cache = new byte[1024];
        //记录已经到达串口COM且未被读取的数据的字节（Byte）数。
        int availableBytes = 0;

        //如果是数据可用的时间发送，则进行数据的读写
        if(event.getEventType() == SerialPortEvent.DATA_AVAILABLE){
            try {
                availableBytes = inputStream.available();
                while(availableBytes > 0){
                    inputStream.read(cache);
                    //for(int i = 0; i < cache.length && i < availableBytes; i++){
                        //解码并输出数据
                    	
                        System.out.print(new String(cache).trim());
                        String s=new String(cache).trim();
                        int n= Integer.parseInt(s);
                        ShowJP.paint[500]=n;
                        for(int i = 0;i<=499;i++){
                        	ShowJP.paint[i] = ShowJP.paint[i+1];
                        }
                   // }
                    availableBytes = inputStream.available();
                }
                System.out.println();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
	
    /*
	 *  checkPort()方法的定义
	 *  端口检查方法
	 */
	private void checkPort() { 
		if (commPort == null)
			throw new RuntimeException(
					"端口选择出错请用selectPort(String portName)方法选择正确的端口！");

		if (serialPort == null) {
			throw new RuntimeException("SerialPort 对象无效！");
		}
	}

	/*
	 * close()方法的定义
	 * 端口关闭方法
	 */
	public void close() {
		serialPort.close();
		serialPort = null;
		commPort = null;
	}

/*
 * 类的主方法，Java程序运行首先是从这里开始的
 */
	public static void main(String[] args) {  
		for(int i = 0;i<501;i++){
			ShowJP.paint[i] = 0;
		}
		ComPortRead sp = new ComPortRead();
		sp.listPort();
		new ShowJP().CreateJFrame("探测距离");
		System.out.println("选择数据读取串口(COMX)：");
		try{
			String com = scan.nextLine();
			System.out.println("端口选择完毕!");
			System.out.println("以下为所选端口读取的数据：");
			sp.selectPort(com);   //选择COM口交换数据数
		}catch(NoSuchElementException e){
			e.printStackTrace();
		}catch(IllegalStateException e){
			e.printStackTrace();
		}
	}
}