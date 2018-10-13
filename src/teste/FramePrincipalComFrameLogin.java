package teste;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.plaf.basic.BasicInternalFrameUI;

@SuppressWarnings("serial")
public class FramePrincipalComFrameLogin extends JFrame {

	private JPanel contentPane;
	private JDesktopPane desktopPane;
    private InternalFrameUm frameUm;
    private InternalFrameDois frameDois;
    private InternalFrameTres frameTres;
    JMenuBar mnbBarraMenu;
    JMenuItem mniFrameUm;
    JMenuItem mniFrameDois;
    InternalFrameAdapter adapter;
    private JMenuItem mniLogin;
    private boolean usuarioLogado;
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FramePrincipalComFrameLogin frame = new FramePrincipalComFrameLogin();
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
	public FramePrincipalComFrameLogin() {
		setTitle("Plano de Execu\u00E7\u00F5es");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 704, 484);
		
		mnbBarraMenu = new JMenuBar();
		setJMenuBar(mnbBarraMenu);
		
		JMenu mnuMenu = new JMenu("Arquivo");
		mnbBarraMenu.add(mnuMenu);
		
		mniFrameUm = new JMenuItem("Item 1");
		mniFrameUm.setEnabled(false);
		mniFrameUm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				exibePanel1();
			}
		});
		
		mniLogin = new JMenuItem("login");
		mniLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				exibeLogin();
			}
		});
		mnuMenu.add(mniLogin);
		mnuMenu.add(mniFrameUm);
		
		mniFrameDois = new JMenuItem("item 2");
		mniFrameDois.setEnabled(false);
		mniFrameDois.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				exibePanel2();
			}
		});
		mnuMenu.add(mniFrameDois);
		
		JMenuItem mniItem3 = new JMenuItem("item 3");
		//mniItem3.setEnabled(false);
		mniItem3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				exibePanel3();
			}
		});
		mnuMenu.add(mniItem3);
		
		JMenu mnFerramentas = new JMenu("Ferramentas");
		mnbBarraMenu.add(mnFerramentas);
		
		JMenu mnAjuda = new JMenu("Ajuda");
		mnbBarraMenu.add(mnAjuda);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		desktopPane = new JDesktopPane();
		contentPane.add(desktopPane, BorderLayout.CENTER);
		
		/*=======================================================
		  			C´O D I G O  P E R S O N A L I Z A D O
		  ======================================================*/
		
		/* listener nos Internal Frames para observar 
		  quando são fechados e exibir barra de menu */
        adapter = new InternalFrameAdapter() {
            public void internalFrameClosed(InternalFrameEvent e) {
            	mnbBarraMenu.setVisible(true);
            	//Se estiver logadoin habilita itens de menu
            	if(isUsuarioLogado()){
            		mniFrameUm.setEnabled(true);
            	}
            }
        };        
        /* fim listenar */
	}
	
	private void exibeLogin()	{
		/*setUsuarioLogado(false);
		
		IFrameLogin frameLogin = new IFrameLogin(this);
		frameLogin.addInternalFrameListener(adapter);
	    desktopPane.add(frameLogin);	
	    frameLogin.setVisible(true);	
	    
	    formataFrameInterno(frameLogin);
	     
	    mnbBarraMenu.setVisible(false);	*/
	}
	
	private void exibePanel1()	{
		if(frameUm == null){
			frameUm = new InternalFrameUm();
			frameUm.addInternalFrameListener(adapter);		    
		    desktopPane.add(frameUm);
		    frameUm.setVisible(true);
		}else{
			 frameUm.setVisible(true);
			desktopPane.add(frameUm);
		   
		}	
		formataFrameInterno(frameUm);
		mnbBarraMenu.setVisible(false);		
    }
		
	
	private void exibePanel2()	{
		if(frameDois == null){
			frameDois = new InternalFrameDois();
			frameDois.addInternalFrameListener(adapter);
			frameDois.setVisible(true);
            desktopPane.add(frameDois);
        }else {
        	desktopPane.add(frameDois);
        	frameDois.setVisible(true);
        }	
		formataFrameInterno(frameDois);
		mnbBarraMenu.setVisible(false);
    }
	
	private void exibePanel3()	{
		if(frameTres == null){
			frameTres = new InternalFrameTres();
			frameTres.addInternalFrameListener(adapter);
			frameTres.setVisible(true);
            desktopPane.add(frameTres);
	    }else {
	    	desktopPane.add(frameTres);
	    	frameTres.setVisible(true);
	    }	
		formataFrameInterno(frameTres);
		mnbBarraMenu.setVisible(false);		
    }
	
	private void formataFrameInterno(JInternalFrame frameInterno)	{
		// maximiza
	    try {
	    	frameInterno.setMaximum(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}	
	    //retirar o painel superior
	    ((BasicInternalFrameUI)frameInterno.getUI()).setNorthPane(null); 	    
	    //retirar bordas
	    frameInterno.setBorder(null);
	}

	public boolean isUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(boolean usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	
}
