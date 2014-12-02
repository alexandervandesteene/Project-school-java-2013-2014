/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import DAO.Student_IndicatorDAO;
import java.beans.PropertyChangeSupport;

/**
 *
 * @author Kevin
 */
public class Student_Indicator {
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    
    private int stud_id;
    private int ind_id;
    private int score;


    public Student_Indicator(int stud , int ind, int s) {
        stud_id = stud;
        ind_id = ind;
        score = s;
    }

    public Student_Indicator(int score) {
        this.score = score;
    }
    public Student_Indicator(int score,int ind) {
        this.score = score;
        this.ind_id=ind;
    }
 

    public int getStudID() 
        {return stud_id;}
    
    public int getIndID() 
        {return ind_id;}
    
    public int getScore() 
        {return score;}

    public void setScore(int s) 
    {
        int oldScore = this.score;
        score = s;
        changeSupport.firePropertyChange("score", oldScore, score);
    }
    
    public void update(int score)
    {
        Student_IndicatorDAO sidao = new Student_IndicatorDAO();
        sidao.UpdateStudent_Indicator(this, score);
    }

    @Override
    public String toString() 
    {
        String sc = Integer.toString(score);
        return sc;
    }
    
}