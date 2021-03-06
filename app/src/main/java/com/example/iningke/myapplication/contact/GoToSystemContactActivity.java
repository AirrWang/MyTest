package com.example.iningke.myapplication.contact;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.iningke.myapplication.R;
import com.iningke.baseproject.utils.LogUtils;

/**
 * 直接跳转 系统通讯录
 *
 * @author hxy
 * @date 2017/2/14
 */
public class GoToSystemContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_to_system_contact);
    }

    public void goToSystemContact(View view) {
        Intent i = new Intent();
        i.setAction(Intent.ACTION_PICK);
        i.setData(ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(i, 2222);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        if (!(resultCode == RESULT_OK)) {
            return;
        }
        String phoneNumber = null;
        Uri contactData = data.getData();
        if (contactData == null) {
            return;
        }
        Cursor cursor = managedQuery(contactData, null, null, null, null);
        if (cursor.moveToFirst()) {
//                  String name = cursor.getString(cursor
//                          .getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            String hasPhone = cursor
                    .getString(cursor
                            .getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
            String id = cursor.getString(cursor
                    .getColumnIndex(ContactsContract.Contacts._ID));
            if (hasPhone.equalsIgnoreCase("1")) {
                hasPhone = "true";
            } else {
                hasPhone = "false";
            }
            if (Boolean.parseBoolean(hasPhone)) {
                Cursor phones = getContentResolver().query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID
                                + " = " + id, null, null);
                while (phones.moveToNext()) {
                    phoneNumber = phones
                            .getString(phones
                                    .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    LogUtils.e(phoneNumber);
                }
                phones.close();
            }
        }
    }
}
