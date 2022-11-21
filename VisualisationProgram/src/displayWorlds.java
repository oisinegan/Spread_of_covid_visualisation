import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;
import javax.swing.*;

public class displayWorlds extends JFrame implements ActionListener
{
    static ReadFile rf = new ReadFile();
    Integer infected = 0;
    Integer susceptible = 0;
    Integer exposed = 0;
    Integer recovered = 0;
    Integer dead = 0;
    static int pos = 0;
    static Integer worldLoop = 0;
    JButton skipOne = new JButton("Next Gen");
    JButton skipFive = new JButton("Skip Five Gen");
    JButton skipEnd = new JButton("Skip to End");
    static boolean isClicked = true;
    static boolean isSkipEndClicked = false;

	public displayWorlds(int num)
	{
			//JFrame customization
	        JPanel panel = new JPanel();
	        JPanel topPanel = new JPanel();
	        JPanel bottomPanel = new JPanel();
	        getContentPane().setLayout(new BorderLayout());
	        getContentPane().add(topPanel, BorderLayout.NORTH);
	        getContentPane().add(panel, BorderLayout.CENTER);
	        getContentPane().add(bottomPanel, BorderLayout.SOUTH);
	
	        skipOne.addActionListener(this);
	        skipFive.addActionListener(this);
	        skipEnd.addActionListener(this);
	
	        bottomPanel.add(skipOne);
	        bottomPanel.add(skipFive);
	        bottomPanel.add(skipEnd);
	
	        int row = rf.worldSize;
	        int col = rf.worldSize;
	
	        setSize(650, 650);
	        panel.setLayout(new GridLayout(row, col));
	
	        JLabel[][] grid = new JLabel[row][col];
	
	        for (int i = 0; i < row; i++)
	        {
	            for (int j = 0; j < col; j++)
	            {
	            	//Every position create new JLabel
	                grid[i][j] = new JLabel();
	
	                String curWolrd = rf.worlds.get(num);
	                //Display colour instead of letter
	                if (curWolrd.charAt(pos) == 'S')
	                {
	                    grid[i][j].setBackground(Color.orange);
	                    susceptible++;
	                }
	                else if (curWolrd.charAt(pos) == 'E')
	                {
	                    grid[i][j].setBackground(Color.yellow);
	                    exposed++;
	                }
	                else if (curWolrd.charAt(pos) == 'I')
	                {
	                    grid[i][j].setBackground(Color.red);
	                    infected++;
	                }
	                else if (curWolrd.charAt(pos) == 'R')
	                {
	                    grid[i][j].setBackground(Color.green);
	                    recovered++;
	                }
	                else if (curWolrd.charAt(pos) == 'D')
	                {
	                    grid[i][j].setBackground(Color.black);
	                    dead++;
	                }
	                grid[i][j].setOpaque(true);
	                pos++;
	                //Add cell to panel and repeat
	                panel.add(grid[i][j]);
	            }
	        }
	        //Set pos=0 for next generation
	        pos = 0;
	        
	        //JFrame customization
	        String genStr = worldLoop.toString();
	        String infectedStr = infected.toString();
	        String exposedStr = exposed.toString();
	        String susceptibleStr = susceptible.toString();
	        String recoveredStr = recovered.toString();
	        String deadStr = dead.toString();
	
	        JLabel genNLabel = new JLabel("Generation: " + genStr + "  ");
	        JLabel totalNLabel = new JLabel("Totel: " + rf.worldSize * rf.worldSize + "  ");
	        JLabel infectedNLabel = new JLabel("Infected: " + infectedStr + "  ");
	        JLabel exposedNLabel = new JLabel("Exposed: " + exposedStr + "  ");
	        JLabel susceptibleNLabel = new JLabel("Susceptible: " + susceptibleStr + "  ");
	        JLabel recoveredNLabel = new JLabel("Recovered: " + recoveredStr + "  ");
	        JLabel deadNLabel = new JLabel("Dead: " + deadStr + "  ");
	
	        susceptibleNLabel.setBackground(Color.orange);
	        susceptibleNLabel.setOpaque(true);
	        exposedNLabel.setBackground(Color.yellow);
	        exposedNLabel.setOpaque(true);
	        infectedNLabel.setBackground(Color.red);
	        infectedNLabel.setOpaque(true);
	        recoveredNLabel.setBackground(Color.green);
	        recoveredNLabel.setOpaque(true);
	        deadNLabel.setBackground(Color.black);
	        deadNLabel.setForeground(Color.white);
	        deadNLabel.setOpaque(true);
	
	        topPanel.add(genNLabel);
	        topPanel.add(totalNLabel);
	        topPanel.add(susceptibleNLabel);
	        topPanel.add(exposedNLabel);
	        topPanel.add(infectedNLabel);
	        topPanel.add(recoveredNLabel);
	        topPanel.add(deadNLabel);
	        setVisible(true);
	    }

	public static void main(String[] args)
	{
			//Read and display output from file for spread of covid using cellular automata 
	        rf.readFile();
	        System.out.println("Number of gen: " + rf.generations);
	        System.out.println("World size: " + rf.worldSize + " x " + rf.worldSize);
	
	        while (worldLoop <= rf.generations)
	        {
	            if (isClicked == true)//Skip One gen or skip five gen is clicked
	            {
	                JFrame frame = new displayWorlds(worldLoop);
	                isClicked = false;
	            }
	            else if (isSkipEndClicked == true)
	            {
	                JFrame frame = new displayWorlds(worldLoop);
	                try
	                {
	                    TimeUnit.MILLISECONDS.sleep(200); //wait .2 seconds between JFrame creations
	                }
	                catch (InterruptedException e)
	                {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	                }
	
	                worldLoop++;
	            }
	            else
	            {
	                System.out.println("gen: " + worldLoop);
	            }
	        }
	    }
	
	    @Override public void actionPerformed(ActionEvent e)
	    {
	        if (e.getSource() == skipOne)
	        {
	            worldLoop++;
	            isClicked = true;
	        }
	        else if (e.getSource() == skipFive)
	        {
	            if (rf.generations - worldLoop < 5)
	            {
	                worldLoop = rf.generations;
	            }
	            else
	            {
	                worldLoop = worldLoop + 5;
	            }
	            isClicked = true;
	        }
	        else if (e.getSource() == skipEnd)
	        {
	            isSkipEndClicked = true;
	        }
	    }
}
