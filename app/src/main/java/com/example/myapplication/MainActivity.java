package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String positionChooser;
    ImageView imageStaff;
    Bitmap bitmap;
    ArrayList<Staff> listStaff = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        Button addBtn = findViewById(R.id.addButton);
        Button exitBtn = findViewById(R.id.exitButton);
        Button addImageBtn = findViewById(R.id.addImageButton);
        imageStaff = findViewById(R.id.staffImage);
        EditText idStaff = findViewById(R.id.idStaff);
        EditText nameStaff = findViewById(R.id.nameStaff);
        RadioGroup sexChooser = findViewById(R.id.sexChooser);
        Spinner postionStaff = findViewById(R.id.positionStaff);
        ListView listView = findViewById(R.id.listViewStaff);
        ArrayList<String> listPosition = new ArrayList<>();
        listPosition.add("Nhân viên văn phòng");
        listPosition.add("Nhân viên kinh doanh");
        listPosition.add("Nhân viên kế toán");
        PositonList list = new PositonList(listPosition);
        postionStaff.setAdapter(list);
        postionStaff.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                android.widget.Adapter adapter = adapterView.getAdapter();
                positionChooser = listPosition.get(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioButton sex = findViewById(sexChooser.getCheckedRadioButtonId());
                Staff staff = new Staff(Long.parseLong(idStaff.getText().toString()),nameStaff.getText().toString(), sex.getText().toString(), positionChooser, bitmap);
                listStaff.add(staff);
                ListStaffAdapter listViewStaff = new ListStaffAdapter(listStaff);
                listView.setAdapter(listViewStaff);
            }
        });
        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });
        addImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.chooser_image_view);
                LinearLayout galleryChoose = dialog.findViewById(R.id.galleryChoose);
                LinearLayout cameraChoose = dialog.findViewById(R.id.cameraChoose);
                galleryChoose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_PICK);
                        intent.setType("image/*");
                        startActivityForResult(intent, 9999);
                        Toast.makeText(MainActivity.this, "Thư viện", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });
                cameraChoose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, 5555);
                        dialog.cancel();
                        Toast.makeText(MainActivity.this, "Máy ảnh", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object o = listView.getItemAtPosition(i);
                Staff staff = (Staff) o;
                imageStaff.setImageBitmap(staff.getLinkImage());
                idStaff.setText(Long.toString(staff.getIdStaff()));
                nameStaff.setText(staff.getNameStaff());
                positionChooser = staff.getPositionStaff();
                RadioButton radioButtonMale = findViewById(R.id.male);
                RadioButton radioButtonFemale = findViewById(R.id.female);
                if(staff.getSexStaff().equals("Nam"))
                    radioButtonMale.setChecked(true);
                else
                    radioButtonFemale.setChecked(true);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 5555)
        {
           bitmap = (Bitmap) data.getExtras().get("data");
            imageStaff.setImageBitmap(bitmap);
        }
        if(requestCode == 9999)
        {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                bitmap = BitmapFactory.decodeStream(inputStream);
                imageStaff.setImageBitmap(bitmap);
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
        }
    }
}