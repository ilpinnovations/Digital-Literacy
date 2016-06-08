package com.tcs.innovations.digitalliteracy.Adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.tcs.innovations.digitalliteracy.Beans.CourseBean;
import com.tcs.innovations.digitalliteracy.Interfaces.Communicator;
import com.tcs.innovations.digitalliteracy.R;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecycleViewHolder> {

    LayoutInflater layoutInflater;
    ArrayList<CourseBean> courseBeanList = new ArrayList<CourseBean>();
    Communicator communicator;

    public RecyclerAdapter(Context context, ArrayList<CourseBean> courseBeanList) {

        this.layoutInflater = LayoutInflater.from(context);
        this.courseBeanList = courseBeanList;
        this.communicator = (Communicator) context;
    }

    @Override
    public RecyclerAdapter.RecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View cardView;
        int cardNumber = viewType + 1;

        if (cardNumber > 6) {
            cardNumber = cardNumber % 6;
        }

        switch (cardNumber) {
            case 1:
                cardView = layoutInflater.inflate(R.layout.course_card1, parent, false);
                break;
            case 2:
                cardView = layoutInflater.inflate(R.layout.course_card2, parent, false);
                break;
            case 3:
                cardView = layoutInflater.inflate(R.layout.course_card3, parent, false);
                break;
            case 4:
                cardView = layoutInflater.inflate(R.layout.course_card4, parent, false);
                break;
            default:
                cardView = layoutInflater.inflate(R.layout.course_card1, parent, false);
        }

        RecycleViewHolder recycleViewHolder = new RecycleViewHolder(cardView);

        Log.d("cardNumber", String.valueOf(cardNumber));

        return recycleViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.RecycleViewHolder holder, int position) {
        final CourseBean currentData = courseBeanList.get(position);
        holder.courseName.setText(currentData.getCourseName());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                communicator.callBack(currentData);

                Log.d("courseID", currentData.getCourseID());
                Log.d("courseName", currentData.getCourseName());
            }
        });

    }

    @Override
    public int getItemCount() {
        return courseBeanList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public class RecycleViewHolder extends RecyclerView.ViewHolder {

        TextView courseName;
        CardView cardView;

        public RecycleViewHolder(View itemView) {
            super(itemView);
            courseName = (TextView) itemView.findViewById(R.id.courseName);
            cardView = (CardView) itemView.findViewById(R.id.CV);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}