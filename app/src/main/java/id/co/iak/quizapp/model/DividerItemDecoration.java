package id.co.iak.quizapp.model;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by mochadwi on 5/4/17.
 */

/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class DividerItemDecoration extends RecyclerView.ItemDecoration {

	private static final int[] ATTRS = new int[]{
			android.R.attr.listDivider
	};

	// Const
	public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;

	public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;

	private Drawable mDivider;

	private int mOrientation;

	/**
	 * as Constructor to build override DividerItemDecoration
	 *
	 * @param context,     current activity context
	 * @param orientation, desired orientation such as: VERTICAL or HORIZONTAL
	 */
	public DividerItemDecoration(Context context, int orientation) {
		final TypedArray a = context.obtainStyledAttributes(ATTRS);
		mDivider = a.getDrawable(0);
		a.recycle();
		setOrientation(orientation);
	}

	/**
	 * Set the current DividerItemDecoration orientation
	 *
	 * @param orientation, desired orientation such as: VERTICAL or HORIZONTAL
	 */
	public void setOrientation(int orientation) {
		if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
			throw new IllegalArgumentException("invalid orientation");
		}
		mOrientation = orientation;
	}

	/**
	 * Draw the custom DividerItemDecoration
	 *
	 * @param c,      canvas
	 * @param parent, RecyclerView target layout
	 * @param state,  current state of RecyclerView item
	 */
	@Override
	public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
		super.onDraw(c, parent, state);
		if (mOrientation == VERTICAL_LIST) {
			drawVertical(c, parent);
		} else {
			drawHorizontal(c, parent);
		}
	}

	/**
	 * Draw the HORIZONTAL DividerItemDecoration
	 * The RecyclerView items is vertically served
	 *
	 * @param c,      canvas
	 * @param parent, RecyclerView target parent layout
	 */
	public void drawVertical(Canvas c, RecyclerView parent) {
		final int left = parent.getPaddingLeft();
		final int right = parent.getWidth() - parent.getPaddingRight();

		final int childCount = parent.getChildCount();
		for (int i = 0; i < childCount; i++) {
			final View child = parent.getChildAt(i);
			final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
					.getLayoutParams();
			final int top = child.getBottom() + params.bottomMargin;
			final int bottom = top + mDivider.getIntrinsicHeight();
			mDivider.setBounds(left, top, right, bottom);
			mDivider.draw(c);
		}
	}

	/**
	 * Draw the VERTICAL orientation
	 * The RecyclerView items is horizontally served
	 *
	 * @param c,      canvas
	 * @param parent, RecyclerView target parent layout
	 */

	public void drawHorizontal(Canvas c, RecyclerView parent) {
		final int top = parent.getPaddingTop();
		final int bottom = parent.getHeight() - parent.getPaddingBottom();

		final int childCount = parent.getChildCount();
		for (int i = 0; i < childCount; i++) {
			final View child = parent.getChildAt(i);
			final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
					.getLayoutParams();
			final int left = child.getRight() + params.rightMargin;
			final int right = left + mDivider.getIntrinsicHeight();
			mDivider.setBounds(left, top, right, bottom);
			mDivider.draw(c);
		}
	}

	/**
	 * Set offsets between RecyclerView item
	 *
	 * @param outRect, prepare rectangle boundaries
	 * @param view,    parent view
	 * @param parent,  RecyclerView target parent layout
	 * @param state,   current state of RecyclerView item
	 */
	@Override
	public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
		super.getItemOffsets(outRect, view, parent, state);
		if (mOrientation == VERTICAL_LIST) {
			outRect.set(0, mDivider.getIntrinsicHeight() + 16,
					0, mDivider.getIntrinsicHeight() + 16);
		} else {
			outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
		}
	}
}
