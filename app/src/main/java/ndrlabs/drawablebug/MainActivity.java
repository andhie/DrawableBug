package ndrlabs.drawablebug;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewGroup container1 = (ViewGroup) findViewById(R.id.container1);
        ViewGroup container2 = (ViewGroup) findViewById(R.id.container2);
        ViewGroup container3 = (ViewGroup) findViewById(R.id.container3);

        getLayoutInflater().inflate(R.layout.sub_view, container1, true);
        getLayoutInflater().inflate(R.layout.sub_view, container2, true);
        getLayoutInflater().inflate(R.layout.sub_view, container3, true);

        setupLayout(container1, false, false);
        setupLayout(container2, true, false);
        setupLayout(container3, false, true);

    }

    private void setupLayout(View view, boolean shouldMutate, boolean shouldSetBackground) {
        TextView textView = (TextView) view.findViewById(R.id.text1);
        ImageView imageView = (ImageView) view.findViewById(R.id.image1);
        FrameLayout framelayout = (FrameLayout) view.findViewById(R.id.layout);

        textView.setOnClickListener(this);
        imageView.setOnClickListener(this);
        framelayout.setOnClickListener(this);

        textView.setCompoundDrawablesWithIntrinsicBounds(null, null, getTintedDrawable(shouldMutate), null);
        imageView.setImageDrawable(getTintedDrawable(shouldMutate));
        framelayout.setBackgroundDrawable(getTintedDrawable(shouldMutate));

        if (shouldSetBackground) {
            int resId = getSelectableItemBackground();
            textView.setBackgroundResource(resId);
            imageView.setBackgroundResource(resId);
            framelayout.setForeground(ContextCompat.getDrawable(this, resId));
        }
    }

    private Drawable getTintedDrawable(boolean shouldMutate) {
        ColorStateList stateList = getResources().getColorStateList(R.color.icon_tint_selector);

        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.ic_action_bug_report);
        drawable = DrawableCompat.wrap(drawable);
        if (shouldMutate) drawable = drawable.mutate();
        DrawableCompat.setTintList(drawable, stateList);
        return drawable;
    }

    /**
     * Returns the {@link android.support.v7.appcompat.R.attr#selectableItemBackground}.
     * A {@link ColorStateList} with all transparent color will also work as suggested by
     * <a href="https://code.google.com/p/android/issues/detail?id=172067#c6" />
     *
     * @return
     */
    @DrawableRes
    private int getSelectableItemBackground() {
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.selectableItemBackground, typedValue, true);
        return typedValue.resourceId;
    }

    @Override
    public void onClick(View v) {
        /* no-op */
    }
}
