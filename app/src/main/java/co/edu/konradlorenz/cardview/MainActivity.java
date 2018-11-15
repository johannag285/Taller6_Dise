package co.edu.konradlorenz.cardview;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SeriesAdapter adapter;
    private List<Serie> serieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initCollapsingToolbar();


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        serieList = new ArrayList<>();
        adapter = new SeriesAdapter(this, serieList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareAlbums();

        try {
            Glide.with(this).load(R.mipmap.fondofinal).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     */
    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    /**
     * Adding few albums for testing
     */
    private void prepareAlbums() {
        int[] covers = new int[]{
                R.mipmap.serie1,
                R.mipmap.serie2,
                R.mipmap.serie3,
                R.mipmap.serie4,
                R.mipmap.serie5,
                R.mipmap.serie6,
                R.mipmap.serie7,
                R.mipmap.serie8,
                R.mipmap.serie9,
                R.mipmap.serie10

        };



        Serie Johanna = new Serie("Dark", 1, covers[0],"La desaparición de dos niños muestra los vínculos entre cuatro familias y expone el pasado de una pequeña ciudad");
        serieList.add(Johanna);

        Johanna = new Serie("13 Reason why", 2, covers[1],"Después de que una mujer joven se quita la vida, su compañero de clase encuentra una misteriosa caja en su patio");
        serieList.add(Johanna);

        Johanna = new Serie("Orange is the new Black", 5, covers[2],"Condenado por un crimen de una década de transportar dinero de la droga a una ex novia, normalmente Piper Chapman es condenado a un año y medio tras las rejas para enfrentar la realidad de lo que realmente puede ser una prisión que cambia la vida");
        serieList.add(Johanna);
        Johanna = new Serie("Stranger Things", 2, covers[3],"Unos niños empiezan a notar comportamientos raros en el lugar donde viven donde tienen que encontrar una solucion a un gran problema");
        serieList.add(Johanna);

        Johanna = new Serie("La casa de papel", 2, covers[4],"Unos ladrones se infiltran en un banco el cual intentan robar de una manera muy astuta, podran ejecutar lo que planean");
        serieList.add(Johanna);

        Johanna = new Serie("Games of thrones", 7, covers[5],"Está serie, muestra la competencia entre familias nobles de siete reinos de Westeros, cuya finalidad es ganar el control sobre el Trono de Hierro");
        serieList.add(Johanna);

        Johanna = new Serie("Elite", 1, covers[6],"Las Encinas es el colegio más exclusivo de España, el lugar donde estudian los hijos de la élite y donde acaban de ser admitidos tres jóvenes de clase baja, procedentes de un colegio público en ruinas");
        serieList.add(Johanna);

        Johanna = new Serie("Insaciable ", 1, covers[7],"Está serie cuenta la historia de Patty, una adolescente con sobrepeso que sufre las burlas de sus compañeros del colegio, hasta que adelgaza durante unas vacaciones de verano");
        serieList.add(Johanna);

        Johanna = new Serie("The Walking Dead", 9, covers[8],"Tras estar en estado de coma, el policía Rick Grimes descubre que una enfermedad originó un apocalipsis zombi. Rick liderará un grupo de sobrevivientes para buscar un lugar seguro, pero las luchas más peligrosas surgirán entre ellos mismos");
        serieList.add(Johanna);

        Johanna = new Serie("The Crown", 2, covers[9],"Una crónica de la vida de la reina Isabel II de los años 40 a los tiempos modernos. A medida que pasan las décadas, se revelan intrigas personales, romances y rivalidades políticas");
        serieList.add(Johanna);


        adapter.notifyDataSetChanged();
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
