package com.example.worktask;

import com.example.worktask.Models.Operations;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    Operations operations=new Operations();
    @Test
    public void operation__is_correct()
    {
        operations.do_operations(20.0,"+");
        double result=operations.getResult_value();
        assertEquals("result plus test failed",20.0,result,0.0);
        operations.do_operations(5.0,"*");
        double result2=operations.getResult_value();
        assertEquals("result multiply test failed",100.0,result2,0.0);
        operations.do_operations(5.0,"/");
        double result3=operations.getResult_value();
        assertEquals("result div test failed",20.0,result3,0.0);
        operations.do_operations(5.0,"-");
        double result4=operations.getResult_value();
        assertEquals("result div test failed",15.0,result4,0.0);
    }
    @Test
    public void undo_is_correct()
    {
        operations.do_operations(20.0,"+");
        operations.do_operations(80.0,"-");
        operations.do_operations(90.0,"*");
         operations.undo_operation();
        double result=operations.getResult_value();
        assertEquals("undo  test failed",-60,result,0.0);


    }
    @Test
    public void undo_is_correct_complex()
    {
        operations.do_operations(20.0,"+");
        operations.do_operations(80.0,"-");
        operations.do_operations(90.0,"*");
        operations.do_operations(100.0,"/");
        operations.do_operations(500.0,"/");
        operations.do_operations(600.0,"*");
        operations.undo_operation();
        operations.undo_operation();
        operations.undo_operation();
        double result=operations.getResult_value();
        assertEquals("undo  test failed",-5400,result,0.0);
    }
    @Test
    public void redo_is_correct_()
    {
        operations.do_operations(20.0,"+");
        operations.do_operations(80.0,"-");
        operations.do_operations(90.0,"*");
        operations.do_operations(100.0,"/");
        operations.do_operations(500.0,"/");
        operations.do_operations(600.0,"*");
        operations.undo_operation();
        operations.undo_operation();
        operations.redo_operation();
        operations.undo_operation();
        operations.redo_operation();
        double result=operations.getResult_value();
        assertEquals("undo  test failed",-0.108,result,0.0);
    }
}