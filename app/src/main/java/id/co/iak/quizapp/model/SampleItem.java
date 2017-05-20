package id.co.iak.quizapp.model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mikepenz.fastadapter.items.AbstractItem;

import java.util.List;

import id.co.iak.quizapp.R;

/**
 * Created by mochadwi on 5/3/17.
 */

public class SampleItem extends AbstractItem<SampleItem, SampleItem.ViewHolder> {
	public String name;
	public String description;

	public SampleItem(String name, String description) {
		this.name = name;
		this.description = description;
	}

	//The unique ID for this type of item
	@Override
	public int getType() {
		return R.id.ll_sample_item;
	}

	//The layout to be used for this type of item
	@Override
	public int getLayoutRes() {
		return R.layout.sample_item;
	}

	//The logic to bind your data to the view
	@Override
	public void bindView(ViewHolder viewHolder, List<Object> payloads) {
		//call super so the selection is already handled for you
		super.bindView(viewHolder, payloads);

		//bind our data
		//set the text for the name
		viewHolder.name.setText(name);
		//set the text for the description or hide
		viewHolder.description.setText(description);
	}

	//reset the view here (this is an optional method, but recommended)
	@Override
	public void unbindView(ViewHolder holder) {
		super.unbindView(holder);
		holder.name.setText(null);
		holder.description.setText(null);
	}

	//Init the viewHolder for this Item
	@Override
	public ViewHolder getViewHolder(View v) {
		return new ViewHolder(v);
	}

	//The viewHolder used for this item. This viewHolder is always reused by the RecyclerView so scrolling is blazing fast
	protected static class ViewHolder extends RecyclerView.ViewHolder {
		protected TextView name;
		protected TextView description;

		public ViewHolder(View view) {
			super(view);
			this.name = (TextView) view.findViewById(R.id.txt_name);
			this.description = (TextView) view.findViewById(R.id.txt_description);
		}
	}
}
