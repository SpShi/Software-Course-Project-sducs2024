package com.fate.movie.action;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;


/**
 * 生成验证码
 */
@WebServlet(urlPatterns = "/code.let",loadOnStartup = 1)
public class ValCodeServlet extends HttpServlet {
        Random random =new Random();
    /**
     * 获取随机字符串
     * @return
     */
    private String getRandomStr(){
        String str="23456789ABCDEFGHJKMNPQRSTUVWXYZabcdefghkmnopqrstuvwxyz";//1,0,l o
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<4;i++){
            int index = random.nextInt(str.length());

            char letter = str.charAt(index);
            sb.append(letter);
        }
        return sb.toString();
    }

    /**
     * 获取背景颜色 0~ 255
     * @return
     */
    private Color getBackColor(){
        int red = random.nextInt(255);
        int green = random.nextInt(255);
        int blue = random.nextInt(255);

        return new Color(red,green,blue);

    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Random random=new Random();
        //	            默认背景为黑色
        //设置响应格式为图片:jpg
        resp.setContentType("image/jpeg");
        //图片对象
        BufferedImage bufferedImage = new BufferedImage(80,30,BufferedImage.TYPE_INT_RGB);

        //获取画布对象
        Graphics graphics = bufferedImage.getGraphics();
        Color bgColor = getBackColor();
        graphics.setColor(bgColor);
        graphics.fillRect(0,0, 80, 30);
        String randomStr = getRandomStr();
        HttpSession session = req.getSession();
        session.setAttribute("code",randomStr);
        for(int i=0;i<=3;i++)
        {
//			设置随机的颜色
            graphics.setColor(new Color(random.nextInt(256),random.nextInt(256),random.nextInt(256)));
            graphics.setFont(new Font("微软雅黑",Font.BOLD,30));
            char c=randomStr.charAt(i);
            graphics.drawString(c+"", i*16, 23+random.nextInt(7));
        }
//		画干扰线
        int max=random.nextInt(10);
        for(int i=0;i<max;i++)
        {
            graphics.setColor(new Color(random.nextInt(256),random.nextInt(256),random.nextInt(256)));
            graphics.drawLine(random.nextInt(80),random.nextInt(30), random.nextInt(80), random.nextInt(30));
        }
//		画干扰点
        int max2=random.nextInt(10);
        for(int i=0;i<max2;i++)
        {
            graphics.setColor(new Color(random.nextInt(256),random.nextInt(256),random.nextInt(256)));
            graphics.drawOval(random.nextInt(80), random.nextInt(30), random.nextInt(5), random.nextInt(10));
        }


        ServletOutputStream servletOutputStream=resp.getOutputStream();
        ImageIO.setUseCache(false);
        ImageIO.write(bufferedImage,"jpg",servletOutputStream);
        graphics.dispose();
    }
}
