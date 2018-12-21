package com.example.actionview;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ActionView extends LinearLayout {

    public ImageView ivActionImage;
    public TextView tvActionTitle;
    public TextView tvActionSubtitle;
    public TextView actionClickView;

    public ActionClickListener actionClickListener;


    public ActionView(Context context) {
        super(context);
        init(context, null, 0, 0);
    }

    public ActionView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0, 0);
    }

    public ActionView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ActionView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, defStyleRes);
    }

    public void init(Context context, @Nullable AttributeSet attrs, @Nullable int defStyleAttr, @Nullable int defStyleRes) {
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.ActionView, defStyleAttr, defStyleRes);


        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.action_view_layout, this, true);

        setOrientation(LinearLayout.VERTICAL);
        setGravity(Gravity.CENTER);

        ivActionImage = findViewById(R.id.ivActionImage);
        tvActionTitle = findViewById(R.id.tvActionTitle);
        tvActionSubtitle = findViewById(R.id.tvActionSubtitle);
        actionClickView = findViewById(R.id.actionClickView);


        try {
            /**
             * Set default image resource
             */
            int imageRes = a.getResourceId(R.styleable.ActionView_image, R.drawable.action_view_cloud);
            if (imageRes != 0) {
                setActionImage(imageRes);
            }

            /**
             * Set default image tint
             */
            int imageTint = a.getColor(R.styleable.ActionView_imageTint, 0);
            if (imageTint != 0) {
                setActionImageTint(imageTint);
            }

            /**
             * Set default image visibility
             */
            boolean imageVisible = a.getBoolean(R.styleable.ActionView_imageVisible, true);
            setActionImageVisible(imageVisible);

            /**
             * Set default image size
             */
            int imageSize = a.getDimensionPixelSize(R.styleable.ActionView_imageSize, 0);
            if (imageSize != 0) {
                setActionImageSize(imageSize);
            }


            /**
             * Set default action title
             */
            String title = a.getString(R.styleable.ActionView_title);
            if (title != null) {
                setActionTitle(title);
            }


            /**
             * Set default action title color
             */
            int titleColor = a.getColor(R.styleable.ActionView_titleColor,
                    getResources().getColor(R.color.actionViewDefaultTitleColor));
            tvActionTitle.setTextColor(titleColor);


            /**
             * Set default action title visibility
             */
            boolean titleVisible = a.getBoolean(R.styleable.ActionView_titleVisible, true);
            if (!titleVisible) {
                tvActionTitle.setVisibility(View.GONE);
            }


            /**
             * Set default action subtitle text
             */
            String subtitle = a.getString(R.styleable.ActionView_subtitle);
            if (subtitle != null) {
                setActionSubtitle(subtitle);
            }

            /**
             * Set default action subtitle color
             */
            int subtitleColor = a.getColor(R.styleable.ActionView_subtitleColor,
                    getResources().getColor(R.color.actionViewDefaultSubtitleColor));
            tvActionSubtitle.setTextColor(subtitleColor);

            /**
             * Set default action subtitle visibility
             */
            boolean subtitleVisible = a.getBoolean(R.styleable.ActionView_subtitleVisible, true);
            if (!subtitleVisible) {
                tvActionSubtitle.setVisibility(View.GONE);
            }

            /**
             * Set default onAction button visibility
             */
            boolean retryVisible = a.getBoolean(R.styleable.ActionView_actionVisible, true);
            if (!retryVisible) {
                actionClickView.setVisibility(View.GONE);
            }

            /**
             * Set default onAction button text
             */
            String retryText = a.getString(R.styleable.ActionView_actionText);
            if (retryText != null) {
                actionClickView.setText(retryText);
            }

            /**
             * Set default onAction button background
             */
            int retryBackground = a.getResourceId(R.styleable.ActionView_actionBackground,
                    R.drawable.action_button_background);
            actionClickView.setBackgroundResource(retryBackground);


            /**
             * Set default onAction button color
             */
            int retryColor = a.getColor(R.styleable.ActionView_actionColor,
                    getResources().getColor(R.color.actionViewDefaultActionTextColor));
            actionClickView.setTextColor(retryColor);

            /**
             * Set default actionview background color
             */
            int actionviewBackground = a.getColor(R.styleable.ActionView_defaultActionBackground,
                    getResources().getColor(R.color.actionViewDefaultActionViewBackgroundColor));
            this.setBackgroundColor(actionviewBackground);


            /**
             * On click listener for onAction button
             */
            actionClickView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    actionClickListener.onAction();
                }
            });


        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            a.recycle();
        }
    }



    public String getActionTitle() {
        return tvActionTitle.getText().toString();
    }

    public ActionView setActionTitle(String title) {
        tvActionTitle.setText(title);
        return this;
    }

    public ActionView setActionTitleColor(int color){
        tvActionTitle.setTextColor(color);
        return this;
    }

    public String getActionSubtitle() {
        return tvActionSubtitle.getText().toString();
    }

    public ActionView setActionSubtitle(String subtitle) {
        tvActionSubtitle.setText(subtitle);
        return this;
    }

    public ActionView setActionSubtitleColor(int color) {
        tvActionSubtitle.setTextColor(color);
        return this;
    }

    /**
     *
     * @param imageSize
     * @return ActionView for the builder
     */
    public ActionView setActionImageSize(int imageSize) {
        ivActionImage.setLayoutParams(new LayoutParams(convertDpToPixel(imageSize), convertDpToPixel(imageSize)));
        return this;
    }

    public ActionView setActionImageVisible(boolean imageVisible) {
        ivActionImage.setVisibility(imageVisible ? VISIBLE : GONE);
        return this;
    }

    public ActionView setActionImageTint(int imageTint) {
        ivActionImage.setColorFilter(imageTint, PorterDuff.Mode.SRC_ATOP);
        return this;
    }

    public ActionView setActionImage(int imageRes) {
        ivActionImage.setImageDrawable(getResources().getDrawable(imageRes));
        return this;
    }

    public ActionView setActionText(String actionText){
        actionClickView.setText(actionText);
        return this;
    }

    public ActionView setActionTextColor(int color){
        actionClickView.setTextColor(color);
        return this;
    }
    public ActionView setActionVisibility(boolean isActionEnable) {
        actionClickView.setVisibility(isActionEnable ? VISIBLE: GONE);
        return this;
    }
    public ActionView setActionListener(ActionClickListener actionClickListener) {
        this.actionClickListener = actionClickListener;
        return this;
    }

    public ActionView setActionBackgroundColor(int color){
        this.setBackgroundColor(color);
        return this;
    }


    public int convertDpToPixel(float dp){
        Resources resources = this.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return (int) px;
    }
}
