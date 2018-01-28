package nikhil.bhople.expandablerecyclerview;

import android.os.Build;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by NIkhil on 28-01-2018.
 */

public class ParentRVAdapter extends RecyclerView.Adapter<ParentRVAdapter.ViewHolder> {
    private final MainActivity mainActivity;
    private final String[] list;
    private int mExpandedPosition = -1;
    private RecyclerView recyclerView;
    private boolean isExpanded;
    private String[] childList = {"Inside one",
                    "Inside two" ,
                    "Inside three" ,
                    "Inside four" ,
                    "Inside five" ,
                    "Inside six"};

    public ParentRVAdapter(MainActivity mainActivity, String[] list) {

        this.mainActivity = mainActivity;
        this.list = list;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }


    @Override
    public ParentRVAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.parent_rv_single_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ParentRVAdapter.ViewHolder holder, int position) {
        holder.serviceName.setText(list[position]);

        // setting adapter for child recyclerview
        holder.childRecyclerView.setHasFixedSize(true);
        holder.childRecyclerView.setLayoutManager(new GridLayoutManager(mainActivity,2));
        holder.childRecyclerView.setAdapter(new ChildRVAdapter(childList, mainActivity));


        isExpanded = position==mExpandedPosition;
        holder.childRecyclerView.setVisibility(isExpanded?View.VISIBLE:View.GONE);
        holder.itemView.setActivated(isExpanded);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView serviceName;
        RecyclerView childRecyclerView;

        public ViewHolder(View itemView) {
            super(itemView);

            serviceName = itemView.findViewById(R.id.tv_name);
            childRecyclerView = itemView.findViewById(R.id.child_recyclerview);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            // for animation
            mExpandedPosition=isExpanded?-1:getAdapterPosition();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                TransitionManager.beginDelayedTransition(recyclerView);
            }
            notifyDataSetChanged();

        }
    }
}
