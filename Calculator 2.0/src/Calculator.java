import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;


public class Calculator extends JFrame
{
	private JTextArea calcArg;
	private JTextArea calcText;
	private GridBagConstraints frameLayout;
	private JButton but0;
	private JButton but1;
	private JButton but2;
	private JButton but3;
	private JButton but4;
	private JButton but5;
	private JButton but6;
	private JButton but7;
	private JButton but8;
	private JButton but9;
	private JButton deci;
	private JButton mult;
	private JButton plus;
	private JButton sub;
	private JButton div;
	private JButton back;
	private JButton clear;
	private JButton enter;
	private JButton clearTable;
	private double num1 = 0.0;
	private double num2 = 0.0;
	private double answer = 0.0;
	private String argument = null;
	private String equal = "=";
	private JTable calcOut;
	private DefaultTableModel tableModel;
	
	public Calculator()
	{
		super("Calculator");
		setSize(600, 400);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		setLayout(new GridBagLayout());
		GridBagConstraints frameLayout = new GridBagConstraints();
		
		frameLayout.fill = GridBagConstraints.BOTH;
		
		frameLayout.gridx = 0; 
		frameLayout.gridy = 0;
		frameLayout.weightx = 1; 
		frameLayout.weighty = .2;
		frameLayout.gridwidth = 3;
		frameLayout.gridheight = 1;		
		add(new CalcPanel(), frameLayout);
		
		frameLayout.gridx = 1; 
		frameLayout.gridy = 1;
		frameLayout.weightx = 1; 
		frameLayout.weighty = 1;
		frameLayout.gridwidth = 1;
		frameLayout.gridheight = 1;
		add(new ButtonPanel(), frameLayout);
		
		frameLayout.gridx = 2; 
		frameLayout.gridy =1;
		frameLayout.weightx = 1; 
		frameLayout.weighty = 1;
		frameLayout.gridwidth = 1;
		frameLayout.gridheight = 1;
		add(new OutputPanel(), frameLayout);
	}
	
	public class PanelTemplate extends JPanel
	{
		private Border raisedbevel, loweredbevel, compound;
		
		public PanelTemplate()
		{
			
			raisedbevel = BorderFactory.createRaisedBevelBorder();
			loweredbevel = BorderFactory.createLoweredBevelBorder();
			compound = BorderFactory.createCompoundBorder(raisedbevel, loweredbevel);
			
			setBackground(Color.DARK_GRAY);
			setForeground(Color.BLACK);
			setBorder(compound);
			setPreferredSize(getPreferredSize());
		
			setLayout(new GridBagLayout());
			frameLayout = new GridBagConstraints();
			frameLayout.fill = GridBagConstraints.BOTH;
		
			frameLayout.gridx = 1; 
			frameLayout.gridy = 1;
			frameLayout.weightx = 1; 
			frameLayout.weighty = 1;
			frameLayout.gridwidth = 1;
			frameLayout.gridheight = 1;
		}
	}
	
	public class CalcPanel extends PanelTemplate
	{
		public CalcPanel()
		{
			frameLayout.gridx = 0; 
			frameLayout.gridy = 0;
			
			calcArg = new JTextArea();
			calcArg.setText("");
			calcArg.setEditable(false);
			calcArg.setBackground(Color.DARK_GRAY);
			calcArg.setForeground(Color.BLACK);
			calcArg.setFont(new Font("Tahoma", Font.BOLD, 20));
			calcArg.setOpaque(true);
			add(calcArg);
			
			frameLayout.gridx = 1; 
			frameLayout.gridy = 0;
			
			calcText = new JTextArea();
			calcText.setText("");
			calcText.setEditable(false);
			calcText.setBackground(Color.DARK_GRAY);
			calcText.setForeground(Color.GREEN);
			calcText.setAlignmentX(Component.RIGHT_ALIGNMENT);
			calcText.setFont(new Font("Tahoma", Font.BOLD, 26));
			calcText.setOpaque(true);
			add(calcText);
		}
	}
	
	public class ButtonTemp extends JButton
	{
		public ButtonTemp()
		{
			setFont(new Font("Tahoma", Font.BOLD, 16));
			setBackground(Color.LIGHT_GRAY);
			setForeground(Color.DARK_GRAY);
		}
	}
	
	public class ButtonPanel extends PanelTemplate implements ActionListener
	{
		public ButtonPanel()
		{
			clear = new ButtonTemp();
			clear.setText("Clr");
			frameLayout.gridx = 0; 
			frameLayout.gridy = 0;
			frameLayout.gridwidth = 2;
			add(clear, frameLayout);
			clear.addActionListener(this);
			
			clearTable = new ButtonTemp();
			clearTable.setText("Clr Tbl");
			frameLayout.gridx = 2; 
			frameLayout.gridy = 0;
			frameLayout.gridwidth = 2;
			add(clearTable, frameLayout);
			clearTable.addActionListener(this);
			
			but7 = new ButtonTemp();
			but7.setText("7");
			frameLayout.gridx = 0; 
			frameLayout.gridy = 1;
			frameLayout.gridwidth = 1;
			but7.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_7, 0), "7");
			but7.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD7, 0), "7");
			but7.getActionMap().put("7", new ButtonAction());
			add(but7, frameLayout);
			but7.addActionListener(this);
			
			but8 = new ButtonTemp();
			but8.setText("8");
			frameLayout.gridx = 1; 
			frameLayout.gridy = 1;
			frameLayout.gridwidth = 1;
			but8.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_8, 0), "8");
			but8.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD8, 0), "8");
			but8.getActionMap().put("8", new ButtonAction());
			add(but8, frameLayout);
			but8.addActionListener(this);
			
			but9 = new ButtonTemp();
			but9.setText("9");
			frameLayout.gridx = 2; 
			frameLayout.gridy = 1;
			frameLayout.gridwidth = 1;
			but9.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_9, 0), "9");
			but9.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD9, 0), "9");
			but9.getActionMap().put("9", new ButtonAction());
			add(but9, frameLayout);
			but9.addActionListener(this);
			
			div = new ButtonTemp();
			div.setText("/");
			frameLayout.gridx = 3; 
			frameLayout.gridy = 1;
			frameLayout.gridwidth = 1;
			div.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DIVIDE, 0), "/");
			div.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SLASH, 0), "/");
			div.getActionMap().put("/", new ButtonAction());
			add(div, frameLayout);
			div.addActionListener(this);
			
			but4 = new ButtonTemp();
			but4.setText("4");
			frameLayout.gridx = 0; 
			frameLayout.gridy = 2;
			frameLayout.gridwidth = 1;
			but4.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_4, 0), "4");
			but4.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD4, 0), "4");
			but4.getActionMap().put("4", new ButtonAction());
			add(but4, frameLayout);
			but4.addActionListener(this);
			
			but5 = new ButtonTemp();
			but5.setText("5");
			frameLayout.gridx = 1; 
			frameLayout.gridy = 2;
			frameLayout.gridwidth = 1;
			but5.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_5, 0), "5");
			but5.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD5, 0), "5");
			but5.getActionMap().put("5", new ButtonAction());
			add(but5, frameLayout);
			but5.addActionListener(this);
			
			but6 = new ButtonTemp();
			but6.setText("6");
			frameLayout.gridx = 2; 
			frameLayout.gridy = 2;
			frameLayout.gridwidth = 1;
			but6.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_6, 0), "6");
			but6.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD6, 0), "6");
			but6.getActionMap().put("6", new ButtonAction());
			add(but6, frameLayout);
			but6.addActionListener(this);
			
			mult = new ButtonTemp();
			mult.setText("*");
			frameLayout.gridx = 3; 
			frameLayout.gridy = 2;
			frameLayout.gridwidth = 1;
			mult.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ASTERISK, 0), "*");
			mult.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_MULTIPLY, 0), "*");
			mult.getActionMap().put("*", new ButtonAction());
			add(mult, frameLayout);
			mult.addActionListener(this);
			
			but1 = new ButtonTemp();
			but1.setText("1");
			frameLayout.gridx = 0; 
			frameLayout.gridy = 3;
			frameLayout.gridwidth = 1;
			but1.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_1, 0), "1");
			but1.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD1, 0), "1");
			but1.getActionMap().put("1", new ButtonAction());
			add(but1, frameLayout);
			but1.addActionListener(this);
			
			but2 = new ButtonTemp();
			but2.setText("2");
			frameLayout.gridx = 1; 
			frameLayout.gridy = 3;
			frameLayout.gridwidth = 1;
			but2.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_2, 0), "2");
			but2.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD2, 0), "2");
			but2.getActionMap().put("2", new ButtonAction());
			add(but2, frameLayout);
			but2.addActionListener(this);
			
			but3 = new ButtonTemp();
			but3.setText("3");
			frameLayout.gridx = 2; 
			frameLayout.gridy = 3;
			frameLayout.gridwidth = 1;
			but3.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_3, 0), "3");
			but3.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD3, 0), "3");
			but3.getActionMap().put("3", new ButtonAction());
			add(but3, frameLayout);	
			but3.addActionListener(this);
			
			sub = new ButtonTemp();
			sub.setText("-");
			frameLayout.gridx = 3; 
			frameLayout.gridy = 3;
			frameLayout.gridwidth = 1;
			sub.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, 0), "-");
			sub.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SUBTRACT, 0), "-");
			sub.getActionMap().put("-", new ButtonAction());
			add(sub, frameLayout);
			sub.addActionListener(this);
			
			back = new ButtonTemp();
			back.setText("Del");
			frameLayout.gridx = 4; 
			frameLayout.gridy = 1;
			frameLayout.gridwidth = 1;
			frameLayout.gridheight = 2;
			back.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0), "Backspace");
			back.getActionMap().put("Backspace", new ButtonAction());
			add(back, frameLayout);
			back.addActionListener(this);
			
			enter = new ButtonTemp();
			enter.setText("=");
			frameLayout.gridx = 4; 
			frameLayout.gridy = 3;
			frameLayout.gridwidth = 1;
			frameLayout.gridheight = 2;
			enter.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, 0), ".");
			enter.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), ".");
			enter.getActionMap().put(".", new ButtonAction());
			add(enter, frameLayout);
			enter.addActionListener(this);
			
			but0 = new ButtonTemp();
			but0.setText("0");
			frameLayout.gridx = 0; 
			frameLayout.gridy = 4;
			frameLayout.gridwidth = 2;
			frameLayout.gridheight = 1;
			but0.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_0, 0), "0");
			but0.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD0, 0), "0");
			but0.getActionMap().put("0", new ButtonAction());
			add(but0, frameLayout);
			but0.addActionListener(this);
			
			deci = new ButtonTemp();
			deci.setText(".");
			frameLayout.gridx = 2; 
			frameLayout.gridy = 4;
			frameLayout.gridwidth = 1;
			deci.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DECIMAL, 0), ".");
			deci.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_PERIOD, 0), ".");
			deci.getActionMap().put(".", new ButtonAction());
			add(deci, frameLayout);
			deci.addActionListener(this);
			
			plus = new ButtonTemp();
			plus.setText("+");
			frameLayout.gridx = 3; 
			frameLayout.gridy = 4;
			frameLayout.gridwidth = 1;
			frameLayout.gridheight = 1;
			plus.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_PLUS, 0), "+");
			plus.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ADD, 0), "+");
			plus.getActionMap().put("+", new ButtonAction());
			add(plus, frameLayout);
			plus.addActionListener(this);
		}

		public void actionPerformed(ActionEvent e)
		{
			DecimalFormat df = new DecimalFormat("#.########");
			JButton clicked = (JButton)e.getSource();
			
			if(clicked == but0)
			{
				calcText.append("0");
			}
			else if(clicked == but1)
			{
				calcText.append("1");
			}
			else if(clicked == but2)
			{
				calcText.append("2");
			}
			else if(clicked == but3)
			{
				calcText.append("3");
			}
			else if(clicked == but4)
			{
				calcText.append("4");
			}
			else if(clicked == but5)
			{
				calcText.append("5");
			}
			else if(clicked == but6)
			{
				calcText.append("6");
			}
			else if(clicked == but7)
			{
				calcText.append("7");
			}
			else if(clicked == but8)
			{
				calcText.append("8");
			}
			else if(clicked == but9)
			{
				calcText.append("9");
			}
			else if(clicked == deci)
			{
				String d = calcText.getText();
				if(d.contains("."))
				{
				}
				else
				{
					calcText.append(".");
				}
			}
			else if(clicked == plus)
			{
				argument = "+";
				num1 = Double.parseDouble(calcText.getText());
				calcArg.setText(df.format(num1) + " + ");
				calcText.setText("");
			}
			else if(clicked == sub)
			{
				argument = "-";
				num1 = Double.parseDouble(calcText.getText());
				calcArg.setText(df.format(num1) + " + ");
				calcText.setText("");
			}
			else if(clicked == mult)
			{
				argument = "*";
				num1 = Double.parseDouble(calcText.getText());
				calcArg.setText(df.format(num1) + " * ");
				calcText.setText("");
			}
			else if(clicked == div)
			{
				argument = "/";
				num1 = Double.parseDouble(calcText.getText());
				calcArg.setText(df.format(num1) + " / ");
				calcText.setText("");
			}
			else if(clicked == back)
			{
				if(calcText.getText().length() > 0)
				{
					String backspace = calcText.getText().substring(0, calcText.getText().length()-1);
					calcText.setText(backspace);
				}
				else
				{
					calcText.setText("");
				}
			}
			else if(clicked == clear)
			{
				num1 = 0.0;
				num2 = 0.0;
				argument = null;
				calcArg.setText("");
				calcText.setText("");
			}
			else if(clicked == clearTable)
			{
				tableModel.setRowCount(0);
			}
			else if((clicked == enter) && (calcText.getText() != ""))
			{
				num2 = Double.parseDouble(calcText.getText());
				
				if(argument == "+")
				{
					answer = num1 + num2;
					calcArg.setText(df.format(num1) + " " + argument + " " + df.format(num2) + " = ");
					calcText.setText(df.format(answer));
				}
				else if(argument == "-")
				{
					answer = num1 - num2;
					calcArg.setText(df.format(num1) + " " + argument + " " + df.format(num2) + " = ");
					calcText.setText(df.format(answer));
				}
				else if(argument == "*")
				{
					answer = num1 * num2;
					calcArg.setText(df.format(num1) + " " + argument + " " + df.format(num2) + " = ");
					calcText.setText(df.format(answer));
				}
				else if(argument == "/")
				{
					answer = num1 / num2;
					calcArg.setText(df.format(num1) + " " + argument + " " + df.format(num2) + " = ");
					calcText.setText(df.format(answer));
				}
				else
				{
					System.out.println("Invalid input.");
				}
				Object[] newRow = {df.format(num1), argument, df.format(num2), "=", df.format(answer)};
				tableModel.addRow(newRow);
			}
			else if((clicked == enter) && (calcText.getText() == ""))
			{
				calcText.setText(Double.toString(num1));
			}
		}
	}
	
	public class ButtonAction extends AbstractAction
	{
		public void actionPerformed(ActionEvent e)
		{
			JButton temp = (JButton)e.getSource();
			temp.doClick();
		}
	}
	
	public class OutputPanel extends PanelTemplate
	{
		private String[] colNames = {"Num 1", "", "Num 2", "", "Answer"};
		private int cellRow;
		private int cellCol;
		
		public OutputPanel()
		{
			JScrollPane scrollPane = new JScrollPane();
			JMenuItem copyCell = new JMenuItem("copy selected cell to calculator");
			JPopupMenu popupCopy = new JPopupMenu();
			popupCopy.add(copyCell);
			
			scrollPane.getViewport().setBackground(Color.DARK_GRAY);
			scrollPane.getViewport().setForeground(Color.LIGHT_GRAY);
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			add(scrollPane, frameLayout);
			
			tableModel = new DefaultTableModel(colNames, 0);
			calcOut = new JTable(tableModel);
			calcOut.getColumnModel().getColumn(1).setPreferredWidth(10);
			calcOut.getColumnModel().getColumn(3).setPreferredWidth(10);
			calcOut.setBackground(Color.LIGHT_GRAY);
			calcOut.setForeground(Color.BLACK);
			
			calcOut.addMouseListener(new MouseAdapter()
			{
				public void mousePressed(MouseEvent ev)
				{
					if(ev.getButton() == MouseEvent.BUTTON1)
					{
						cellRow = calcOut.getSelectedRow();
						cellCol = calcOut.getSelectedColumn();
						if(cellCol == 0 || cellCol == 2 || cellCol == 4)
						{
							popupCopy.show(calcOut, ev.getX(), ev.getY());
						}
					}
				}
			});
			
			copyCell.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					Object cell = calcOut.getValueAt(cellRow, cellCol);
					calcText.setText(String.valueOf(cell));
				}
			});
			
			scrollPane.getViewport().add(calcOut);
		}
	}
}
















