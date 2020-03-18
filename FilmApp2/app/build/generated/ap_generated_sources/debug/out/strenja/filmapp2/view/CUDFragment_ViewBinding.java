// Generated code from Butter Knife. Do not modify!
package strenja.filmapp2.view;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import strenja.filmapp2.R;

public class CUDFragment_ViewBinding implements Unbinder {
  private CUDFragment target;

  private View view7f0800bc;

  @UiThread
  public CUDFragment_ViewBinding(final CUDFragment target, View source) {
    this.target = target;

    View view;
    target.naslov = Utils.findRequiredViewAsType(source, R.id.naslov, "field 'naslov'", EditText.class);
    target.redatelj = Utils.findRequiredViewAsType(source, R.id.redatelj, "field 'redatelj'", EditText.class);
    target.datumIzlaskaFilma = Utils.findRequiredViewAsType(source, R.id.datumIzlaska, "field 'datumIzlaskaFilma'", TextView.class);
    target.dropDownMenu = Utils.findRequiredViewAsType(source, R.id.spinner, "field 'dropDownMenu'", Spinner.class);
    target.slika = Utils.findRequiredViewAsType(source, R.id.slikaFilma, "field 'slika'", ImageView.class);
    target.noviFilm = Utils.findRequiredViewAsType(source, R.id.noviFilm, "field 'noviFilm'", Button.class);
    target.uslikaj = Utils.findRequiredViewAsType(source, R.id.uslikaj, "field 'uslikaj'", Button.class);
    target.promjenaFilma = Utils.findRequiredViewAsType(source, R.id.promjenaFilma, "field 'promjenaFilma'", Button.class);
    target.obrisiFilm = Utils.findRequiredViewAsType(source, R.id.obrisiFilm, "field 'obrisiFilm'", Button.class);
    view = Utils.findRequiredView(source, R.id.nazad, "method 'nazad'");
    view7f0800bc = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.nazad();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    CUDFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.naslov = null;
    target.redatelj = null;
    target.datumIzlaskaFilma = null;
    target.dropDownMenu = null;
    target.slika = null;
    target.noviFilm = null;
    target.uslikaj = null;
    target.promjenaFilma = null;
    target.obrisiFilm = null;

    view7f0800bc.setOnClickListener(null);
    view7f0800bc = null;
  }
}
