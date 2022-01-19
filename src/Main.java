
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
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Home
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    private Properties properties;
    private FileProperties fileProperties;
    private String host;
    private int port;
    public static void main(String[] args) throws ExceptionPayment, IOException, InterruptedException {
        new Main().run();
    }
    
    
    public void run() throws ExceptionPayment, IOException, InterruptedException{
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        this.fileProperties = new FileProperties();
        this.properties = this.fileProperties.getConfiguration();
        this.host = this.properties.getProperty("host");
        this.port = Integer.parseInt(this.properties.getProperty("port"));
        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup);
            serverBootstrap.channel(NioServerSocketChannel.class);
            //serverBootstrap.handler(new LoggingHandler(LogLevel.INFO));
            serverBootstrap.childHandler(new NettyInitializer());
            serverBootstrap.option(ChannelOption.SO_BACKLOG, 128);
            serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture f = serverBootstrap.bind(this.port).sync();
            System.out.println("*************************************************");
            System.out.println("**********Server is started : port "+this.port+"**********");
            System.out.println("*************************************************");
            f.channel().closeFuture().sync();
            
        }finally{
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
    
}
