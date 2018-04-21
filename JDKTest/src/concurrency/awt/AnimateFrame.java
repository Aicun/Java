package concurrency.awt;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AnimateFrame extends JFrame {
	
	public static final int DEFAULT_WIDTH = 300;
	public static final int DEFAULT_HEIGHT = 300;
	
	public AnimateFrame(){
		ArrayComponent component = new ArrayComponent();
		add(component,BorderLayout.CENTER);
		
		final Sorter sorter = new Sorter(component);
		
		JButton runButton = new JButton("run");
		runButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				sorter.setRun();
			}
			
		});
		
		JButton stepButton = new JButton("step");
		stepButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				sorter.setStep();
			}
			
		});
		
		JPanel buttons = new JPanel();
		buttons.add(runButton);
		buttons.add(stepButton);
		
		add(buttons,BorderLayout.NORTH);
		setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
		
		Thread t = new Thread(sorter);
		t.start();
	}
}
