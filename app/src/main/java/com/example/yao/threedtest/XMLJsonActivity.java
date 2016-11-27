package com.example.yao.threedtest;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import adapter.Book;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import parser.BookParser;
import parser.DomBookParser;
import parser.PullBookParser;
import parser.SaxBookParser;

/**
 * Created by Yao on 2016/11/10.
 */
public class XMLJsonActivity extends AppCompatActivity {

    @InjectView(R.id.star_xml_sax)
    Button starXmlSax;
    @InjectView(R.id.star_xml_dom)
    Button starXmlDom;
    @InjectView(R.id.star_xml_pull)
    Button starXmlPull;
    @InjectView(R.id.xml_tv)
    TextView xmlTv;

    private BookParser parser;
    private List<Book> books;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xmljson);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.star_xml_sax, R.id.star_xml_dom, R.id.star_xml_pull})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.star_xml_sax:
                startSax();
                break;
            case R.id.star_xml_dom:
                startDom();
                break;
            case R.id.star_xml_pull:
                startPull();
                break;
        }
    }

    /**
     * Pull解析
     */
    private void startPull() {
        parser = new PullBookParser();
        start(parser);
    }

    /**
     * Dom解析
     */
    private void startDom() {
        parser = new DomBookParser(); //创建SaxBookParser实例
        start(parser);
    }

    /**
     * Sax解析
     */
    private void startSax() {
        parser = new SaxBookParser(); //创建SaxBookParser实例
        start(parser);

    }

    private void start(BookParser parser) {
        try {
            InputStream is = getAssets().open("books.xml");
            books = parser.parse(is);
            for (Book book:books){
                Log.d("测试一下",book.toString());
            }

            String xml = parser.serialize(books);
            xmlTv.setText(xml);
            FileOutputStream fos = openFileOutput("books.xml", Context.MODE_PRIVATE);
            fos.write(xml.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
