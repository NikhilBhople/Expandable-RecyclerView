package nikhil.bhople.expandablerecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by NIkhil on 28-01-2018.
 */

public class ChildRVAdapter extends RecyclerView.Adapter<ChildRVAdapter.ViewHolder> {
    private final String[] childList;
    private final MainActivity mainActivity;

    public ChildRVAdapter(String[] childList, MainActivity mainActivity) {

        this.childList = childList;
        this.mainActivity = mainActivity;
    }

    @Override
    public ChildRVAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.child_rv_single_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ChildRVAdapter.ViewHolder holder, int position) {
        holder.subcategoryName.setText(childList[position]);
    }

    @Override
    public int getItemCount() {
        return childList == null ? 0 : childList.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView subcategoryName;

        public ViewHolder(View itemView) {
            super(itemView);

            subcategoryName = itemView.findViewById(R.id.tv_service_sub_category);

            subcategoryName.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(mainActivity, "You clicked "+childList[getAdapterPosition()], Toast.LENGTH_SHORT).show();
        }
    }
}
