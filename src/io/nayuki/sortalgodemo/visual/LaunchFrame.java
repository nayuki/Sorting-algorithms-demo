/* 
 * Sorting algorithms demo (Java)
 * 
 * Copyright (c) Project Nayuki
 * https://www.nayuki.io/page/sorting-algorithms-demo-java
 * 
 * (MIT License)
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 * - The above copyright notice and this permission notice shall be included in
 *   all copies or substantial portions of the Software.
 * - The Software is provided "as is", without warranty of any kind, express or
 *   implied, including but not limited to the warranties of merchantability,
 *   fitness for a particular purpose and noninfringement. In no event shall the
 *   authors or copyright holders be liable for any claim, damages or other
 *   liability, whether in an action of contract, tort or otherwise, arising from,
 *   out of or in connection with the Software or the use or other dealings in the
 *   Software.
 */

package io.nayuki.sortalgodemo.visual;

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
import java.util.List;
import java.util.Objects;
import io.nayuki.sortalgodemo.core.SortAlgorithm;
import io.nayuki.sortalgodemo.core.SortArray;


/**
 * The window (frame) for choosing which sorting algorithm, array size, etc. to launch.
 */
@SuppressWarnings("serial")
final class LaunchFrame extends Frame implements ActionListener {
	
	/*---- Fields ----*/
	
	private final List<SortAlgorithm> algorithms;
	
	private final Choice algorithmInput;
	private final TextField arraySizeInput;
	private final Choice initialOrder;
	private final TextField scaleInput;
	private final TextField speedInput;
	
	private final Button runButton;
	
	
	
	/*---- Constructor ----*/
	
	public LaunchFrame(List<SortAlgorithm> algos) {
		// Set window title and closing action
		super("Sort Demo");
		Objects.requireNonNull(algos);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		// Create layout manager and set default values
		var gbl = new GridBagLayout();
		var gbc = new GridBagConstraints();
		this.setLayout(gbl);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.ipadx = 0;
		gbc.ipady = 0;
		gbc.insets = new Insets(4, 4, 4, 4);
		gbc.weighty = 0;
		
		
		/*-- First column --*/
		{  // Create and add label elements
			gbc.gridx = 0;
			gbc.weightx = 1;
			gbc.gridy = 0;
			
			var label = new Label("Sort algorithm:");
			gbl.setConstraints(label, gbc);
			this.add(label);
			gbc.gridy++;
			
			label = new Label("Array size:");
			gbl.setConstraints(label, gbc);
			this.add(label);
			gbc.gridy++;
			
			label = new Label("Initial order:");
			gbl.setConstraints(label, gbc);
			this.add(label);
			gbc.gridy++;
			
			label = new Label("Visual scale:");
			gbl.setConstraints(label, gbc);
			this.add(label);
			gbc.gridy++;
			
			label = new Label("Running speed:");
			gbl.setConstraints(label, gbc);
			this.add(label);
			gbc.gridy++;
		}
		
		
		/*-- Second column --*/
		{
			gbc.gridx = 1;
			gbc.weightx = 2;
			gbc.gridy = 0;
			
			// Drop-down selector for sort algorithm
			algorithms = new ArrayList<>(algos);
			algorithmInput = new Choice();
			for (SortAlgorithm algo : algos)
				algorithmInput.add(algo.getName());
			gbl.setConstraints(algorithmInput, gbc);
			this.add(algorithmInput);
			gbc.gridy++;
			
			// Text field for array size
			arraySizeInput = new TextField("30");
			arraySizeInput.addActionListener(this);
			gbl.setConstraints(arraySizeInput, gbc);
			this.add(arraySizeInput);
			gbc.gridy++;
			
			// Drop-down selector for initial order
			initialOrder = new Choice();
			initialOrder.add("Ascending");
			initialOrder.add("Almost ascending");
			initialOrder.add("Random");
			initialOrder.add("Almost descending");
			initialOrder.add("Descending");
			initialOrder.select("Random");
			gbl.setConstraints(initialOrder, gbc);
			this.add(initialOrder);
			gbc.gridy++;
			
			// Text field for scale
			scaleInput = new TextField("12");
			scaleInput.addActionListener(this);
			gbl.setConstraints(scaleInput, gbc);
			this.add(scaleInput);
			gbc.gridy++;
			
			// Text field for speed
			speedInput = new TextField("10");
			speedInput.addActionListener(this);
			gbl.setConstraints(speedInput, gbc);
			this.add(speedInput);
			gbc.gridy++;
		}
		
		// Run button
		runButton = new Button("Run");
		runButton.addActionListener(this);
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridx = 0;
		gbc.gridwidth = 2;
		gbc.weighty = 1;
		gbl.setConstraints(runButton, gbc);
		this.add(runButton);
		gbc.gridy++;
		
		// Do layout and show
		this.pack();
		Rectangle rect = getGraphicsConfiguration().getBounds();
		this.setLocation(
			(rect.width - this.getWidth()) / 2,
			(rect.height - this.getHeight()) / 3);
		this.setVisible(true);
	}
	
	
	
	/*---- Methods ----*/
	
	// Called when the run button is clicked or entered is pressed in a text field.
	// Called by the AWT event loop, not by user code.
	public void actionPerformed(ActionEvent ev) {
		// Parse and check input numbers from text fields
		int size, scale;
		double speed;
		try {
			size = Integer.parseInt(arraySizeInput.getText());
			scale = Integer.parseInt(scaleInput.getText());
			speed = Double.parseDouble(speedInput.getText());
		} catch (NumberFormatException e) {
			return;
		}
		if (size <= 0 || scale <= 0 || speed <= 0 || Double.isInfinite(speed) || Double.isNaN(speed))
			return;
		
		// Initialize objects and worker thread
		final var array = new VisualSortArray(size, speed);
		setInitialOrder(array, initialOrder.getSelectedIndex());
		array.finishInitialization();
		final SortAlgorithm algorithm = algorithms.get(algorithmInput.getSelectedIndex());
		SortCanvas canvas = new SortCanvas(array, scale, 60);
		final int startDelayMs = 1000;
		new Thread() {
			public Thread thread = this;
			
			public void run() {
				initFrame();
				doSort();
			}
			
			private void initFrame() {
				// Do component layout
				final var sortFrame = new Frame(algorithm.getName());
				sortFrame.add(canvas);
				sortFrame.setResizable(false);
				sortFrame.pack();
				
				// Set window closing action
				sortFrame.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
						thread.interrupt();
						sortFrame.dispose();
					}
				});
				
				// Set window position and show
				Rectangle rect = getGraphicsConfiguration().getBounds();
				sortFrame.setLocation(
					(rect.width - sortFrame.getWidth()) / 8,
					(rect.height - sortFrame.getHeight()) / 8);
				sortFrame.setVisible(true);
			}
			
			private void doSort() {
				// Wait and sort
				try {
					Thread.sleep(startDelayMs);
					algorithm.sort(array);
					array.setRange(0, array.length(), SortArray.ElementState.DONE);
				} catch (StopException|InterruptedException e) {
					canvas.stop();
					return;
				}
				
				// Check and print
				String msg = String.format("%s: %d elements, %d comparisons, %d swaps",
					algorithm.getName(), array.length(), array.getComparisonCount(), array.getSwapCount());
				try {
					array.finishSorting();
				} catch (AssertionError e) {
					msg = algorithm.getName() + ": Sorting failed";
				}
				synchronized(System.err) {
					System.err.println(msg);
				}
			}
		}.start();
	}
	
	
	private static void setInitialOrder(VisualSortArray array, int selectedIndex) {
		Objects.requireNonNull(array);
		switch (selectedIndex) {
			case 0: {  // Ascending
				// Do nothing
				break;
			}
			
			case 1: {  // Almost ascending
				for (int i = 0; i < array.length() / 10; i++) {
					for (int j = 0; j < array.length() - 1; j++) {
						if (SortArray.random.nextBoolean())
							array.swap(j, j + 1);
					}
				}
				break;
			}
			
			case 2: {  // Random
				array.shuffle();
				break;
			}
			
			case 3: {  // Almost descending
				setInitialOrder(array, 4);
				setInitialOrder(array, 1);
				break;
			}
			
			case 4: {  // Descending
				for (int i = 0, j = array.length() - 1; i < j; i++, j--)
					array.swap(i, j);
				break;
			}
			
			default:
				throw new IllegalArgumentException();
		}
	}
	
}
