
import com.pgt360.config.NettyInitializer;
import com.pgt360.exception.ExceptionPayment;
import com.pgt360.utils.FileProperties;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import java.io.IOException;
import java.util.Properties;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Home
 */
public class NettyServer {
    private NioEventLoopGroup bossLoopGroup;
    private NioEventLoopGroup workerLoopGroup;
    private ServerBootstrap bootstrap;
    private Properties properties;
    private FileProperties fileProperties;
    private String host;
    private int port;
    public NettyServer(){
        this.bossLoopGroup = new NioEventLoopGroup();
        this.workerLoopGroup = new NioEventLoopGroup();
        this.bootstrap = new ServerBootstrap();
        this.fileProperties = new FileProperties();
    }
    public static void main(String[] args) throws ExceptionPayment, IOException, InterruptedException {
        NettyServer nettyServer = new NettyServer();
        nettyServer.run();
    }
    
    
    public void run() throws ExceptionPayment, IOException, InterruptedException{
        this.properties = this.fileProperties.getConfiguration();
        this.host = this.properties.getProperty("host");
        this.port = Integer.parseInt(this.properties.getProperty("port"));
        try{
            bootstrap.group(bossLoopGroup, workerLoopGroup);
            bootstrap.channel(NioServerSocketChannel.class);
            //serverBootstrap.handler(new LoggingHandler(LogLevel.INFO));
            bootstrap.option(ChannelOption.SO_BACKLOG, 1024);
            bootstrap.option(ChannelOption.TCP_NODELAY, true);
            bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
            bootstrap.childHandler(new NettyInitializer()); 
            ChannelFuture future = bootstrap.bind(this.port).sync();
            if(future.isSuccess()){
                System.out.println("*************************************************");
                System.out.println("**********Server is started : port "+this.port+"**********");
                System.out.println("*************************************************");
            }else{
                System.exit(-1);
            }
            future.channel().closeFuture().sync();
            
        }finally{
            bossLoopGroup.shutdownGracefully();
            workerLoopGroup.shutdownGracefully();
        }
    }
    
}
