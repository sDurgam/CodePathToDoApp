//package com.durga.sph.todoapp;
//
//import android.support.v4.app.DialogFragment;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.view.KeyEvent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.WindowManager;
//import android.view.inputmethod.EditorInfo;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//
///**
// * Created by durga on 12/28/15.
// */
//public class EditNameDialog extends DialogFragment implements TextView.OnEditorActionListener
//{
//    private EditText edititemTxt;
//    private Button saveBtn;
//    int position = 0;
//    EditItem edititemObj;
//
//    public EditNameDialog()
//    {}
//
//    public interface EditNameDialogListener
//    {
//        void onFinishEditDialog(String name, int position);
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
//    {
//        View view = inflater.inflate(R.layout.activity_edit_item, container);
//        saveBtn = (Button) view.findViewById(R.id.saveItem);
//        saveBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v)
//            {
//                OnFinishEditItem();
//            }
//        });
//        edititemTxt = (EditText) view.findViewById(R.id.editItem);
//        edititemTxt.setOnEditorActionListener(this);
//        return view;
//    }
//
//    public static EditNameDialog newInstance(EditItem editObj)
//    {
//        EditNameDialog edititemfrag = new EditNameDialog();
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("EditItem", editObj);
//        edititemfrag.setArguments(bundle);
//        return edititemfrag;
//    }
//
//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
//    {
//        super.onViewCreated(view, savedInstanceState);
//        // Fetch arguments from bundle and set title
//        String title = "Edit item";
//        getDialog().setTitle(title);
//        edititemObj = (EditItem) (getArguments().getSerializable("EditItem"));
//        // Show soft keyboard automatically and request focus to field
//        if(edititemObj != null)
//        {
//            edititemTxt.setText(edititemObj.getName());
//        }
//        edititemTxt.requestFocus();
//        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
//        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//    }
//
//    @Override
//    public void onResume()
//    {
//        // Get existing layout params for the window
//        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
//        // Assign window properties to fill the parent
//        params.width = WindowManager.LayoutParams.MATCH_PARENT;
//        params.height = WindowManager.LayoutParams.MATCH_PARENT;
//        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
//        // Call super onResume after sizing
//        super.onResume();
//    }
//
//    private void OnFinishEditItem()
//    {
//        int length = edititemTxt.getText().length();
//        if(edititemTxt.getSelectionEnd() == length) {
//            EditNameDialogListener listener = (EditNameDialogListener) getActivity();
//            listener.onFinishEditDialog(edititemTxt.getText().toString(), position);
//            dismiss();
//        }
//    }
//
//    @Override
//    public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
//    {
//        if(EditorInfo.IME_ACTION_DONE == actionId)
//        {
//            OnFinishEditItem();
//            return true;
//        }
//        return false;
//    }
//}
