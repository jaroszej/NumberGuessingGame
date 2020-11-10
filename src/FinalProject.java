/* Jason Jaroszewicz
 * CIS 117 Section 18
 * 24 June 2018
 * Final Project
 * This program will try to guess a number 1 through n given a maximum range n. After each guess from
  * the computer, the user will indicate whether the attempted guess was too high, low,
 * or correct. The program will continue until the correct number is guessed.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class FinalProject extends JFrame
{
    public FinalProject()
    {
        this.setTitle("The Number Guessing Game");
        this.setSize(600,700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(true);
        gameContents();
        this.setVisible(true);
    }

    private void gameContents()
    {
        // components
        JLabel title = new JLabel("<html><pre>               ######        ######     <br>" +
                "              ######        ######      <br>       #################################<br>" +
                "      ################################# <br>           ######         ######        <br>" +
                "       <font size='4'>THE NUMBER GUESSING GAME</font>       <br>         ######         ######          <br>" +
                "    ###############################     <br>   ###############################      <br>" +
                "      ######         ######             <br>     ######         ######              </html>");
        JLabel computerGuess = new JLabel(" ʕ·͡ᴥ·ʔ ");
        // computerGuess font
        computerGuess.setFont(computerGuess.getFont().deriveFont(15f));
        JLabel instruct = new JLabel("<html>Enter a number in the text box below. <br>" + "This number will be the upper boundary. <br>" +
                "Think of a number between 0 your number. <br>" + "The bear will try to guess your number.</html>");
        JLabel message = new JLabel("Press START when ready.");
        JLabel minRange = new JLabel("<html><pre><font size='4'>   0 to  </font></html>");
        JTextField maxRange = new JTextField(4);
        JButton low = new JButton("LOW");
        JButton high = new JButton("HIGH");
        JButton correct = new JButton("CORRECT");
        JButton start = new JButton("START");
        final int[] min = new int[1]; // smallest number available to guess
        min[0] = 1;
        final int[] max = new int[1]; // largest number available to guess
        final int[] newNextGuess = new int[1]; // input number

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.weightx = 0.5;
        gc.weighty = 0.5;
        JPanel header = new JPanel(new GridLayout(2,1));
        JPanel range = new JPanel(new GridLayout(1,2));
        JPanel guess = new JPanel();
        JPanel input = new JPanel(new GridLayout(3,2));

        // FinalProject //
        this.add(mainPanel);
        // header //
        header.add(title);
        header.add(instruct);
        // guess //
        guess.add(computerGuess);
        guess.setBackground(Color.LIGHT_GRAY);
        // range //
        range.add(minRange);
        range.add(maxRange);
        // input //
        input.add(low);
        input.add(high);
        input.add(correct);
        input.add(start);
        input.add(message);
        // mainPanel //
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        gc.gridx = 0;
        gc.gridy = 0;
        mainPanel.add(header, gc);
        gc.gridx = 0;
        gc.gridy = 1;
        mainPanel.add(guess, gc);
        gc.gridx = 0;
        gc.gridy = 2;
        mainPanel.add(range, gc);
        gc.gridx = 0;
        gc.gridy = 3;
        mainPanel.add(input, gc);

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int nextGuess;
                String regex = "\\d+";
                if (maxRange.getText().matches(regex))
                {
                    max[0] = Integer.parseInt(maxRange.getText());
                    // invalid range
                    if (max[0] <= 1)
                    {
                        message.setText("Enter only whole numbers greater than 1.");
                    }
                    // valid range
                    else
                    {
                        // takes range and divides by two to guess number in middle of range
                        nextGuess = (max[0] / 2);
                        computerGuess.setText("Is your number " + nextGuess + "?");
                        newNextGuess[0] = nextGuess;
                        message.setText("Press LOW, HIGH, or CORRECT.");
                    }
                }
                // invalid input
                else
                {
                    message.setText("Enter only whole numbers greater than 1.");
                }
            }
        }); // end start button

        low.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String regex = "\\d+";
                if (maxRange.getText().matches(regex))
                {

                    min[0] = newNextGuess[0];
                    if (min[0] == max[0])
                    {
                        message.setText("Cannot guess higher.");
                        computerGuess.setText("Your number is " + newNextGuess[0] + "!");
                        // reset ranges
                        min[0] = 1;
                        newNextGuess[0] = 0;
                        max[0] = 0;
                        }
                    else
                    {
                        // takes new range and divides by two to guess number in middle of range
                        newNextGuess[0] = (min[0] + max[0]) / 2;
                        computerGuess.setText("Is your number " + newNextGuess[0] + "?");
                        message.setText("Press LOW, HIGH, or CORRECT.");
                    }

                }
                // invalid input
                else
                {
                    message.setText("Enter only whole numbers greater than 1.");
                }

            }
        }); // end low button

        high.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String regex = "\\d+";
                if (maxRange.getText().matches(regex))
                {
                    max[0] = newNextGuess[0];
                    if (min[0] == max[0])
                    {
                        message.setText("Cannot guess lower.");
                        computerGuess.setText("Your number must be " + newNextGuess[0] + "!");
                        // reset ranges
                        min[0] = 1;
                        newNextGuess[0] = 0;
                        max[0] = 0;
                    }
                    else
                    {
                        // takes new range and divides by two to guess number in middle of range
                        newNextGuess[0] = (min[0] + max[0]) / 2;
                        computerGuess.setText("Is your number " + newNextGuess[0] + "?");
                        message.setText("Press LOW, HIGH, or CORRECT.");
                    }
                }
                // invalid input
                else
                {
                    message.setText("Enter only whole numbers greater than 1.");
                }

            }
        }); // end high button

        correct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String regex = "\\d+";
                if (maxRange.getText().matches(regex))
                {
                    message.setText("Enter a new number & play again.");
                    computerGuess.setText("ʕ·͡ᴥ·ʔ");
                    JOptionPane win = new JOptionPane();
                    int dialogue = (int) (Math.random() * (9 - 1)) + 1;
                    switch (dialogue)
                    {
                        case 1:

                            JOptionPane.showMessageDialog(null, "ʕ·͡ᴥ·ʔ I win! HUZZAH!",
                                    "Thanks for playing!",1);
                            break;
                        case 2:
                            JOptionPane.showMessageDialog(null, "ʕ·͡ᴥ·ʔ That made me think!",
                                    "Thanks for playing!",1);
                            break;
                        case 3:
                            JOptionPane.showMessageDialog(null, "ʕ·͡ᴥ·ʔ I knew it all along!",
                                    "Thanks for playing!",1);
                            break;
                        case 4:
                            JOptionPane.showMessageDialog(null, "ʕ·͡ᴥ·ʔ That was fun!",
                                    "Thanks for playing!",1);
                            break;
                        case 5:
                            JOptionPane.showMessageDialog(null, "ʕ·͡ᴥ·ʔ HAHA that was easy!",
                                    "Thanks for playing!",1);
                            break;
                        case 6:
                            JOptionPane.showMessageDialog(null, "ʕ·͡ᴥ·ʔ You're tricky!",
                                    "Thanks for playing!",1);
                            break;
                        case 7:
                            JOptionPane.showMessageDialog(null, "ʕ·͡ᴥ·ʔ You would pick that number...",
                                    "Thanks for playing!",1);
                            break;
                        case 8:
                            JOptionPane.showMessageDialog(null, "ʕ·͡ᴥ·ʔ my favorite number!",
                                    "Thanks for playing!",1);
                            break;
                        case 9:
                            JOptionPane.showMessageDialog(null, "ʕ·͡ᴥ·ʔ I did it, Oh boy!",
                                    "Thanks for playing!",1);
                            break;
                    }
                    maxRange.setText("");
                    // reset ranges
                    min[0] = 1;
                    newNextGuess[0] = 0;
                    max[0] = 0;

                }
                // invalid input
                else {
                    message.setText("Enter only whole numbers greater than 1.");
                }
            }
        }); // end correct button
    }

    public static void main(String[] args)
    {
        new FinalProject();
    }
}