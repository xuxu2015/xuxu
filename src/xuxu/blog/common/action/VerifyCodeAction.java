package xuxu.blog.common.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Random;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 生成验证码图片
 * 
 * @author shanjunpeng
 * 
 */
public class VerifyCodeAction extends BaseAction {
	private InputStream imageStream;
	private String verifyCode;
	private static int WIDTH = 80; // 验证码宽度
	private static int HEIGHT = 22; // 验证码高度
	private static int NUM = 4; // 验证码位数
	private static int LINES = 7; // 干扰线数量
	private static int DOTS = 30; // 干扰点数量

	@Override
	public String execute() throws Exception {
		byte[] img = getImg();
		imageStream = new ByteArrayInputStream(img);
		return "success";
	}

	private byte[] getImg() {
		Random r = new Random(System.currentTimeMillis());
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
				BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		char[] chs = new char[NUM];
		int i = 0;
		while (i < NUM) {
			int n = r.nextInt(75) + 48;
			if ((n > 57 && n < 65) || (n > 90) && (n < 97)) {
				continue;
			}
			char c = (char) n;
			// 随机颜色
			g
					.setColor(new Color(r.nextInt(200), r.nextInt(200), r
							.nextInt(200)));
			// 随机字体大小
			g.setFont(new Font(null, Font.BOLD, r.nextInt(3) + 21));

			g.drawString(c + "", 6 + i * 19, r.nextInt(3) + 19);
			chs[i++] = c;
		}
		verifyCode = new String(chs);

		sessionMap.put("verifyCode", verifyCode);

		// 给图片加干扰线
		for (int j = 0; j < LINES; j++) {
			g
					.setColor(new Color(r.nextInt(255), r.nextInt(255), r
							.nextInt(255)));
			g.drawLine(r.nextInt(80), r.nextInt(30), r.nextInt(80), r
					.nextInt(30));
		}
		// 给图片加干扰点
		for (int j = 0; j < DOTS; j++) {
			g
					.setColor(new Color(r.nextInt(155), r.nextInt(155), r
							.nextInt(155)));
			int x = r.nextInt(80);
			int y = r.nextInt(30);
			g.drawLine(x, y, x, y);
		}

		ByteArrayOutputStream os = new ByteArrayOutputStream();
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);

		try {
			encoder.encode(image);
			return os.toByteArray();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public InputStream getImageStream() {
		return imageStream;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

}
