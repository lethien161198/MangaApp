package com.example.mangaapp.common;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewbinding.ViewBinding;

import com.example.mangaapp.R;
import com.example.mangaapp.models.CustomEvent;

import org.greenrobot.eventbus.EventBus;

public abstract class FragmentView<P extends MVP.Presenter,B extends ViewBinding> extends Fragment implements MVP.View<P> {
    private B binding;

    private P presenter;

    public B getBinding() {
        return binding;
    }

    public void setBinding(B binding) {
        this.binding = binding;
    }

    @Override
    public void setPresenter(P presenter) {
        this.presenter = presenter;
    }

    @Override
    public P getPresenter() {
        return presenter;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        DisposableManager.dispose();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setBinding(inflateBinding(inflater,container));
        View view = getBinding().getRoot();
        showProgress();
        init();
        return view;
    }
    @Override
    public void setParameters(Bundle parameters) {
        setArguments(parameters);
    }

    protected abstract B inflateBinding(LayoutInflater inflater, ViewGroup container);

    protected abstract void init();

    public void navigateReplaced(String route) {
        navigateReplaced(route, null);
    }

    @Override
    public void navigateReplaced(String route, Bundle parameters) {
        MVP.View view = AppNavigator.viewWithRoute(route, parameters);
        if (view == null) {
            return;
        }
        replaceFragment((Fragment) view);
    }

    public void navigate(String route) {
        navigate(route, null);
    }

    @Override
    public void navigate(String route, Bundle parameters) {
        MVP.View view = AppNavigator.viewWithRoute(route, parameters);
        if (view == null) {
            return;
        }
        addFragment((Fragment) view);
        Log.d("abcxyz", "sendUrl: "+getActivity().getSupportFragmentManager().getBackStackEntryCount());
    }

    public void addFragment(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }

    public void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    public void showBottomBar(boolean check) {
        EventBus.getDefault()
                .postSticky(new CustomEvent(check));
    }
    public abstract void showProgress();
    public abstract void hideProgress();

}
