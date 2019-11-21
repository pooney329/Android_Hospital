//package com.example.administrator.hospital;
//
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//public class ContentFragment extends Fragment {
//
//    private String title;
//    private int page;
//
//    /* 프레그먼트 생성자는 반드시 인자없는 생성자
//    public SlideFragment(String t, int p) {
//        title = t;
//        page = p;
//    }*/
//    // 정적메소드. 객체생성없이 클래스명으로 호출 가능한 메소드
//    public static ContentFragment newInstance(String t, int p) {
//        ContentFragment fragment = new ContentFragment();
//        Bundle args = new Bundle();
//        args.putString("title", t);
//        args.putInt("page", p);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        page = getArguments().getInt("page");
//        title = getArguments().getString("title");
//
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.content_fragment, container, false);
//        TextView message = (TextView) view.findViewById(R.id.message);
//        message.setText(title + page);
//        return view;
//    }
//}
//
//
//
