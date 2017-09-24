package com.alekseyld.formulaconverter.view.fragment.base;

import com.alekseyld.formulaconverter.presenter.base.BasePresenter;
import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class BaseFragment_MembersInjector<TPresenter extends BasePresenter>
    implements MembersInjector<BaseFragment<TPresenter>> {
  private final Provider<TPresenter> mPresenterProvider;

  public BaseFragment_MembersInjector(Provider<TPresenter> mPresenterProvider) {
    assert mPresenterProvider != null;
    this.mPresenterProvider = mPresenterProvider;
  }

  public static <TPresenter extends BasePresenter> MembersInjector<BaseFragment<TPresenter>> create(
      Provider<TPresenter> mPresenterProvider) {
    return new BaseFragment_MembersInjector<TPresenter>(mPresenterProvider);
  }

  @Override
  public void injectMembers(BaseFragment<TPresenter> instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.mPresenter = mPresenterProvider.get();
  }

  public static <TPresenter extends BasePresenter> void injectMPresenter(
      BaseFragment<TPresenter> instance, Provider<TPresenter> mPresenterProvider) {
    instance.mPresenter = mPresenterProvider.get();
  }
}
