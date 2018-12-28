package com.umeng.soexample.greendao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.umeng.soexample.greendao.bean.Student;
import com.umeng.soexample.greendao.mydao.StudentDao;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.Insert_Btn)
    Button InsertBtn;
    @BindView(R.id.Delete_Btn)
    Button DeleteBtn;
    @BindView(R.id.Update_Btn)
    Button UpdateBtn;
    @BindView(R.id.Select_Btn)
    Button SelectBtn;
    @BindView(R.id.Get_Content)
    TextView GetContent;
    private StudentDao mStuDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mStuDao = MyApp.getDaoSession().getStudentDao();
    }


    @OnClick({R.id.Insert_Btn, R.id.Delete_Btn, R.id.Update_Btn, R.id.Select_Btn, R.id.Get_Content})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Insert_Btn:
                insertStu();
                break;
            case R.id.Delete_Btn:
                deleteStu();
                break;
            case R.id.Update_Btn:
                updateStu();
                break;
            case R.id.Select_Btn:
                selectStu();
                break;
        }
    }
    private void insertStu() {
        Student student = new Student("李广强", "丑八怪", 38);
        long insert = mStuDao.insert(student);
        if (insert > 0) {
            Toast.makeText(this, "插入成功", Toast.LENGTH_SHORT).show();
        }
    }

    //删除数据  删除必须要删除数据库里面存在的数据
    private void deleteStu() {
        mStuDao.deleteByKey(1l);
        Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
    }

    //修改数据
    private void updateStu() {
        //修改必须要修改数据库里面存在的数据
        Student student = mStuDao.load(2l);
        student.setAge(3);
        student.setName("wwd");
        student.setSex("奇丑无比");
        mStuDao.update(student);
    }

    //查询数据
    private void selectStu() {
        //查询所有数据
        GetContent.setText("");
        List<Student> students = mStuDao.loadAll();
        GetContent.setText(students.toString());
    }
}
