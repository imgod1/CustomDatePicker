package com.example.gk.customdatepicker.views;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.example.gk.customdatepicker.R;

/**
 * 项目名称：Ytb_Android
 * 类描述：
 * 创建人：gk
 * 创建时间：2016/7/27 13:49
 * 修改人：gk
 * 修改时间：2016/7/27 13:49
 * 修改备注：
 */
public class CustomDatePickerDialog extends Dialog {
    private TextView txt_title;
    private CustomDatePicker cdatepicker_dialog;
    private TextView txt_cancel;
    private TextView txt_sure;


    public CustomDatePickerDialog(Context context) {
        this(context, R.style.customDialog);
    }

    public CustomDatePickerDialog(Context context, int themeResId) {
        super(context, themeResId);
        initRes();
    }

    /**
     * 设置view
     */
    private void initRes() {
        setContentView(R.layout.dialog_custom_datepicker);
        initView();
    }

    /**
     * 初始化view
     */
    private void initView() {
        Window window = getWindow();
        txt_title = (TextView) window.findViewById(R.id.txt_title);
        cdatepicker_dialog = (CustomDatePicker) window.findViewById(R.id.cdatepicker_dialog);
        txt_cancel = (TextView) window.findViewById(R.id.txt_cancel);
        txt_sure = (TextView) window.findViewById(R.id.txt_sure);
        initEvent();
    }

    /**
     * 选中的年月日
     */
    private int selectYear;
    private int selectMonth;
    private int selectDay;

    /**
     * 初始化监听事件
     */
    private void initEvent() {
        cdatepicker_dialog.setOnSelectChangeListener(new CustomDatePicker.OnSelectChangeListener() {
            @Override
            public void changed(int year, int month, int day) {
                selectYear = year;
                selectMonth = month;
                selectDay = day;
            }
        });
        txt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (onDialogButtonClickListener != null) {
                    onDialogButtonClickListener.negativeClick();
                }
            }
        });

        txt_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                onDialogButtonClickListener.positiveClick(cdatepicker_dialog.getSelectYear(), cdatepicker_dialog.getSelectMonth(), cdatepicker_dialog.getSelectDay());
            }
        });
    }

    /**
     * 回调监听
     */
    public interface OnDialogButtonClickListener {
        public void positiveClick(int year, int month, int day);//确定

        public void negativeClick();//取消
    }

    private OnDialogButtonClickListener onDialogButtonClickListener;

    public OnDialogButtonClickListener getOnDialogButtonClickListener() {
        return onDialogButtonClickListener;
    }

    public void setOnDialogButtonClickListener(OnDialogButtonClickListener onDialogButtonClickListener) {
        this.onDialogButtonClickListener = onDialogButtonClickListener;
    }

    /**
     * Set the range of selectable dates.
     *
     * @param minDay   The day value of minimum date.
     * @param minMonth The month value of minimum date.
     * @param minYear  The year value of minimum date.
     * @param maxDay   The day value of maximum date.
     * @param maxMonth The month value of maximum date.
     * @param maxYear  The year value of maximum date.
     */
    public void setDateRange(int minDay, int minMonth, int minYear, int maxDay, int maxMonth, int maxYear) {
        cdatepicker_dialog.setDateRange(minDay, minMonth, minYear, maxDay,
                maxMonth, maxYear);
    }

    /**
     * @param year  年份
     * @param month 月份
     * @param day   日期
     */
    public void setDefaultDate(int year, int month, int day) {
        cdatepicker_dialog.setDefaultDate(year, month, day);
    }
}
