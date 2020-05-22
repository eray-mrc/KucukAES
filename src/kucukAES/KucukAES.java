package kucukAES;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.crypto.Cipher;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import kucukAES.Sifrele;;

public class KucukAES extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = -4574179449217319913L;
	private JPanel contentPane;
	private JTextField textFieldSifre;
	private JButton btnDosyaSec;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KucukAES frame = new KucukAES();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	private String dosyaAd = null;
	public KucukAES() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {

		try {
			// Set System L&F
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException e) {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// handle exception
		} catch (InstantiationException e) {
			// handle exception
		} catch (IllegalAccessException e) {
			// handle exception
		}


		KucukAES frame = null;
		setTitle("K\u00FC\u00E7\u00FCk AES");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblifre = new JLabel("\u015Eifre");
		lblifre.setBounds(10, 11, 48, 14);
		contentPane.add(lblifre);

		textFieldSifre = new JTextField();
		textFieldSifre.setBounds(10, 36, 188, 20);
		contentPane.add(textFieldSifre);
		textFieldSifre.setColumns(10);

		btnDosyaSec = new JButton("Dosya Se\u00E7");
		btnDosyaSec.setBounds(10, 67, 188, 23);
		contentPane.add(btnDosyaSec);

		JLabel lblDosya = new JLabel("Dosya Se\u00E7ilmedi");
		lblDosya.setBounds(10, 98, 414, 14);
		contentPane.add(lblDosya);

		JButton btnSifrele = new JButton("\u015Eifrele");
		btnSifrele.setBounds(10, 123, 89, 23);
		contentPane.add(btnSifrele);

		JButton btnSifreCoz = new JButton("\u015Eifre \u00C7\u00F6z");
		btnSifreCoz.setBounds(109, 123, 89, 23);
		contentPane.add(btnSifreCoz);

		JLabel lblDurum = new JLabel("Durum : ");
		lblDurum.setBounds(10, 159, 188, 14);
		contentPane.add(lblDurum);

		JLabel lblNewLabel = new JLabel("\u00A9 Eray Mercan");
		lblNewLabel.setBounds(10, 184, 414, 14);
		contentPane.add(lblNewLabel);

		JLabel lblEraymercangmailcom = new JLabel("eraymercan616@gmail.com");
		lblEraymercangmailcom.setBounds(10, 209, 414, 14);
		contentPane.add(lblEraymercangmailcom);

		// Dosya Se�
		btnDosyaSec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dosyaAd = dosyaSec(frame);
				lblDosya.setText(dosyaAd);

			}
		});

		btnSifrele.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


				lblDurum.setText("Durum : �ifreleniyor..");
				try {
					Sifrele.aesSifreleDosya(Cipher.ENCRYPT_MODE, textFieldSifre.getText(), dosyaAd, masaustu(dosyaAd)+".base64");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					lblDurum.setText("Durum : HATA!");
					e1.printStackTrace();

				}

				lblDurum.setText("Durum : Ba�ar�l�");

			}
		});

		btnSifreCoz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				lblDurum.setText("Durum : �ifre ��z�l�yor..");

				try {
					Sifrele.aesSifreleDosya(Cipher.DECRYPT_MODE, textFieldSifre.getText(), dosyaAd, new String(masaustu(dosyaAd).substring(0, masaustu(dosyaAd).length() - 7)));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					lblDurum.setText("Durum : HATA!");
					e1.printStackTrace();

				}
				lblDurum.setText("Durum : Ba�ar�l�");

			}
		});

	}

	// Dosya Se�
	private static String dosyaSec(KucukAES frame) {
		String klasor = "";
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Hedef Dosyay� Se�in");

		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);

		int returnVal = fc.showOpenDialog(frame);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			klasor = fc.getSelectedFile().getAbsolutePath();
		}

		return klasor;

	}

	private static String masaustu(String dosyaAd) {

		String userHomeFolder = System.getProperty("user.home");


		String fileName = "";
		for (int i = 0; i < dosyaAd.length(); i++) {
			if (dosyaAd.charAt(i) == '/') {

				int index = dosyaAd.lastIndexOf('/');
				fileName = "/Desktop/"+dosyaAd.substring(index + 1);
				break;
			} else if (dosyaAd.charAt(i) == '\\') {

				int index = dosyaAd.lastIndexOf("\\");
				fileName = "\\Desktop\\"+dosyaAd.substring(index + 1);
				break;
			}
		}

		fileName = userHomeFolder + fileName;
		return fileName;
	}
}
