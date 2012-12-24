/* 
 * Sort demo main class
 * 
 * Color legend:
 * - Blue: Normal
 * - Green: In final position
 * - Yellow: Comparing
 * - Gray: Inactive
 */

package nayuki.sortdemo;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Rectangle;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import nayuki.sortdemo.algo.*;


@SuppressWarnings("serial")
public final class SortDemo extends Frame implements ActionListener {
	
	public static void main(String[] args) {
		// Set up list of algorithms and go
		List<SortAlgorithm> algos = new ArrayList<SortAlgorithm>();
		Collections.addAll(algos,
			new BubbleSort(),
			new CocktailSort(),
			new SelectionSort(),
			new PancakeSort(),
			new QuasiPancakeSort(),
			new GnomeSort(),
			new InsertionSort(),
			new InsertionSortBinarySearch(),
			new ShellSort(),
			new HeapSort(),
			new QuickSortDoubleEnded(),
			new QuickSortSliding(),
			new StoogeSort(),
			new StupidSort(),
			new BozoSort());
		new SortDemo(algos);
	}
	
	
	
	private List<SortAlgorithm> algorithms;
	
	private TextField arraySizeInput;
	private TextField scaleInput;
	private TextField delayInput;
	private Choice algorithmInput;
	private Button runButton;
	
	
	
	public SortDemo(List<SortAlgorithm> algos) {
		super("Sort Demo");
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		// Add widgets
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		setLayout(gbl);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.ipadx = 0;
		gbc.ipady = 0;
		gbc.insets = new Insets(4, 4, 4, 4);
		gbc.weighty = 0;
		
		// Labels
		Label label;
		gbc.gridx = 0;
		label = new Label("Algorithm:");
		gbc.weightx = 1;
		gbc.gridy = 0;
		gbl.setConstraints(label, gbc);
		add(label);
		
		label = new Label("Array size:");
		gbc.gridy = 1;
		gbl.setConstraints(label, gbc);
		add(label);
		
		label = new Label("Scale:");
		gbc.gridy = 2;
		gbl.setConstraints(label, gbc);
		add(label);
		
		label = new Label("Delay (ms):");
		gbc.gridy = 3;
		gbl.setConstraints(label, gbc);
		add(label);
		
		// Algorithm
		gbc.gridx = 1;
		gbc.weightx = 2;
		algorithms = new ArrayList<SortAlgorithm>(algos);
		algorithmInput = new Choice();
		for (SortAlgorithm algo : algos)
			algorithmInput.add(algo.getName());
		gbc.gridy = 0;
		gbl.setConstraints(algorithmInput, gbc);
		add(algorithmInput);
		
		// Array size
		arraySizeInput = new TextField("32");
		arraySizeInput.addActionListener(this);
		gbc.gridy = 1;
		gbl.setConstraints(arraySizeInput, gbc);
		add(arraySizeInput);
		
		// Scale
		scaleInput = new TextField("8");
		scaleInput.addActionListener(this);
		gbc.gridy = 2;
		gbl.setConstraints(scaleInput, gbc);
		add(scaleInput);
		
		// Delay
		delayInput = new TextField("100");
		delayInput.addActionListener(this);
		gbc.gridy = 3;
		gbl.setConstraints(delayInput, gbc);
		add(delayInput);
		
		// Run button
		runButton = new Button("Run");
		runButton.addActionListener(this);
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 2;
		gbc.weighty = 1;
		gbl.setConstraints(runButton, gbc);
		add(runButton);
		
		// Do layout and show
		pack();
		Rectangle rect = getGraphicsConfiguration().getBounds();
		setLocation((rect.width - getWidth()) / 2, (rect.height - getHeight()) / 3);
		setVisible(true);
	}
	
	
	public void actionPerformed(ActionEvent ev) {
		try {
			int size = Integer.parseInt(arraySizeInput.getText());
			int scale = Integer.parseInt(scaleInput.getText());
			int delay = Integer.parseInt(delayInput.getText());
			if (size <= 0 || scale <= 0 || delay < 0)
				return;
			
			VisualSortArray array = new VisualSortArray(size, scale, delay);
			SortAlgorithm algorithm = algorithms.get(algorithmInput.getSelectedIndex());
			new SortThread(array, algorithm).start();
		} catch (NumberFormatException e) {}
	}
	
}
