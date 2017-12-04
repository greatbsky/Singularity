package xyz.xysc.databinding.adapters;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

/**
 * @author architect.bian
 * @date 2017-12-01 11:54 AM
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ItemHolder> {

    public static class BR {
        public static int item = -1; //recyclerview绑定子项时传递的variable的key
        public static int vm = -1;
        public static int position = -1;
    }

    private final Object viewModel; //处理所有itemLayout的逻辑
    private List<?> data;
    private int layoutItemRes;

    public RecyclerViewAdapter(List<?> data, int layoutItem, Object viewModel) {
        this.data = data;
        this.layoutItemRes = layoutItem;
        this.viewModel = viewModel;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), layoutItemRes, parent, false);
        return new ItemHolder(binding);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        if (BR.item > 0) {
            holder.binding.setVariable(BR.item, data.get(position));
        }
        if (BR.vm > 0) {
            holder.binding.setVariable(BR.vm, this.viewModel);
        }
        if (BR.position > 0) {
            holder.binding.setVariable(BR.position, position);
        }
        holder.binding.executePendingBindings(); //立刻刷新界面
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ItemHolder extends RecyclerView.ViewHolder {

        public final ViewDataBinding binding;

        public ItemHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @BindingAdapter(value = {"layoutManager", "data", "layoutItem", "viewModel"}, requireAll = false)
    public static void setLayoutManager(RecyclerView recyclerView, RecyclerView.LayoutManager layoutManager, List<?> data, int layoutItem, Object viewModel) {
        if (layoutManager != null) {
            recyclerView.setLayoutManager(layoutManager);
        } else {
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), LinearLayout.VERTICAL, false));
        }
        recyclerView.setAdapter(new RecyclerViewAdapter(data, layoutItem, viewModel));

    }

}
