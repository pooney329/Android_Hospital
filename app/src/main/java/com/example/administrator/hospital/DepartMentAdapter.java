//package com.example.administrator.hospital;
//
//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//
//public class DepartMentAdapter extends RecyclerView.Adapter<DepartMentAdapter.MyViewHolder> {
//    private ArrayList<DepartMent> movieList;
//
//    // 내부클래스. 뷰홀더 정의
//    // 뷰홀더 객체 = 아이템 뷰
//    public class MyViewHolder extends RecyclerView.ViewHolder {
//        public TextView title, genre, year;
//
//        public MyViewHolder(View view) {
//            super(view);
//            title = (TextView) view.findViewById(R.id.title);
//            genre = (TextView) view.findViewById(R.id.genre);
//            year = (TextView) view.findViewById(R.id.year);
//        }
//    }
//    // 생성자 정의
//    public DepartMentAdapter(ArrayList<DepartMent> list) {
//        movieList = list;
//    }
//
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        View itemView = LayoutInflater.from(viewGroup.getContext()).
//                inflate(R.layout.tab_fragment1_row, viewGroup, false);
//
//        return new MyViewHolder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(MyViewHolder holder, int position) {
//        Movie movie = movieList.get(position);
//        holder.title.setText(movie.getTitle());
//        holder.genre.setText(movie.getGenre());
//        holder.year.setText(movie.getYear());
//    }
//
//    @Override
//    public int getItemCount() {
//        return movieList.size();
//    }
//}
//
//
//
//
//
//
//
//
//
//
//
