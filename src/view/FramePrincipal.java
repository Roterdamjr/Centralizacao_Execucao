package view;

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

import teste.InternalFrameDois;
import teste.InternalFrameTres;
import teste.InternalFrameUm;
import utilitarios.EscopoGlobal;




@SuppressWarnings("serial")
public class FramePrincipal extends JFrame {
/*	//passagem de prâmetros entre frames 
	private FramePrincipal esteFrame;
	private boolean isLoginValido;
	//
*/	
	private JPanel contentPane;
	private JDesktopPane desktopPane;	
	private InternalFrameRecebimento frameRecebimento;
	
	private InternalFrameUm frameUm;
    private InternalFrameDois frameDois;
    private InternalFrameTres frameTres;
    private JMenuBar mnbBarraMenu;
    private JMenuItem mniframeTriagem;
    private JMenuItem mniFrameDois;
    private InternalFrameAdapter adapter;
    private boolean usuarioLogado;
    private JMenuItem mniTriagem;
    private JMenuItem mniRecebimento;
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FramePrincipal frame = new FramePrincipal();
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
	public FramePrincipal() {
		setTitle("Plano de Execu\u00E7\u00F5es");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mnbBarraMenu = new JMenuBar();
		setJMenuBar(mnbBarraMenu);
		
		JMenu mnExecucao = new JMenu("Execu\u00E7\u00E3o");
		mnbBarraMenu.add(mnExecucao);
		
		
		mniRecebimento = new JMenuItem("Recebimento");
		mniRecebimento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				exibeRecebimento();
			}
		});
		mnExecucao.add(mniRecebimento);

		/*@TESTE
		*/
/*		JMenu mnuMenu = new JMenu("Teste");
		mnbBarraMenu.add(mnuMenu);
		
		mniframeTriagem = new JMenuItem("Item 1");		
		mniframeTriagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				exibePanel1();
			}
		});		
*/
		
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
		setSize(1200, 800);// larguraXaltura
		
		
		/* listener nos Internal Frames para observar 
		  quando são fechados */
        adapter = new InternalFrameAdapter() {
            public void internalFrameClosed(InternalFrameEvent e) {
            	//exibe barra de menu
            	mnbBarraMenu.setVisible(true);
            	
            	/*faz o garbage colecction remover os frames; 
            	o dispose() apenas libera os recursos 
            	e a tela manteria o estado, exibindo os campos eventualmente preenchidos
            	*/
            	frameRecebimento=null;

            	/* @TESTE*/
            	/* frameUm=null;
            	frameDois=null;*/
            }
        };        
        /* fim listenar */
        
        exibeLogin();
        
	}
	

	private void exibeLogin()	{
		InternalFrameLogin frameLogin = new InternalFrameLogin();
		frameLogin.addInternalFrameListener(adapter);	
	    desktopPane.add(frameLogin);
	    frameLogin.setVisible(true);
	    
		formataFrameInterno(frameLogin);
		mnbBarraMenu.setVisible(false);		
		//setTitle(getTitle() + " - " + EscopoGlobal.getLoginUsuario());
	}	
	
	private void exibeRecebimento()	{
		if(frameRecebimento == null){
			frameRecebimento = new InternalFrameRecebimento();
			frameRecebimento.addInternalFrameListener(adapter);		    
		}
		desktopPane.add(frameRecebimento);	
		frameRecebimento.setVisible(true);
		formataFrameInterno(frameRecebimento);
		mnbBarraMenu.setVisible(false);		
    }
	
	/*@TESTE*/
/*	private void exibePanel1()	{
		if(frameUm == null){
			frameUm = new InternalFrameUm();
			frameUm.addInternalFrameListener(adapter);		    
		}
		desktopPane.add(frameUm);	
		frameUm.setVisible(true);
		formataFrameInterno(frameUm);
		mnbBarraMenu.setVisible(false);		
    }
			
*/
	
	private void formataFrameInterno(JInternalFrame frameInterno)	{
		// maximiza
	    try {
	    	frameInterno.setMaximum(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}	
	    //retirar o painel superior
	    //((BasicInternalFrameUI)frameInterno.getUI()).setNorthPane(null); 	    
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
