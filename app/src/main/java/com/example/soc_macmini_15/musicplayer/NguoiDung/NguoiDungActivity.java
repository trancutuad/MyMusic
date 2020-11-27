package com.example.soc_macmini_15.musicplayer.NguoiDung;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.soc_macmini_15.musicplayer.Activity.MainActivity;
import com.example.soc_macmini_15.musicplayer.Dao.NguoiDungDao;
import com.example.soc_macmini_15.musicplayer.Model.Nguoidung;
import com.example.soc_macmini_15.musicplayer.R;

public class NguoiDungActivity extends AppCompatActivity {

    EditText edUserName,edPassword;
    Button btDangnhap,BtDangki;
    NguoiDungDao nguoiDungDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguoi_dung);
        edUserName = (EditText)findViewById(R.id.edtUserName);
        edPassword = (EditText)findViewById(R.id.edtPassword);
        BtDangki = (Button)findViewById(R.id.btnDangki);
        btDangnhap = (Button)findViewById(R.id.btnDangnhap);

        nguoiDungDao = new NguoiDungDao(NguoiDungActivity.this);

        check();

    }
    //dang ki
    public void addUser(View view)
    {
        nguoiDungDao =  new NguoiDungDao (NguoiDungActivity.this);
        Nguoidung user = new Nguoidung(edUserName.getText().toString(), edPassword.getText().toString());

        try {
            if (nguoiDungDao.inserNguoiDung(user)>0){
                Toast.makeText(getApplicationContext(),"Them thanh cong",Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(getApplicationContext(),"Them that bai",Toast.LENGTH_LONG).show();
            }
        }catch (Exception ex){
            Log.e("Error", ex.toString());
        }

    }
    // login

    public void check(){
        btDangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = edUserName.getText().toString().trim();
                String passworl =edPassword.getText().toString().trim();
                if (passworl.length() <6 || username.isEmpty() || passworl.isEmpty() ){

                    if (username.isEmpty())
                        edUserName.setError("Không được để trống tài khoản");
                    if (passworl.isEmpty())
                        edPassword.setError("Không được để trống mật khẩu");
                    if (passworl.length()<6)
                        edPassword.setError("Mật khẩu phải trên 6 kí tự");
                }
                else {
                    // db người dùng
                    Nguoidung user = nguoiDungDao.getUser(username);
                    if (user != null && user.getUserName() != null) {
                        if (passworl.matches(user.getPassword())) {
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();
                            Toast.makeText(NguoiDungActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        } else {

                            Toast.makeText(NguoiDungActivity.this, "Tài khoản hoặc mật khẩu chưa chính xác", Toast.LENGTH_SHORT).show();

                        }
                    } else {
                        Toast.makeText(NguoiDungActivity.this, "Bạn chưa có tài khoản", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });
    }


}