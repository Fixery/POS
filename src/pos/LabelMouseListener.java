package pos;
/*
 * Class yang digunakan untuk mendeteksi posisi mouse
 */
import javax.swing.Timer;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LabelMouseListener extends MouseAdapter
{
	Timer waktu;
	
	public LabelMouseListener(Timer waktuParam)
	{
		waktu = waktuParam;
	}
	
	public void mouseEntered(MouseEvent me)
	{
		waktu.stop();
	}
	public void mouseExited(MouseEvent me)
	{
		waktu.start();
	}
}
