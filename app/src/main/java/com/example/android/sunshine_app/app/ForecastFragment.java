package com.example.android.sunshine_app.app;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A forecast fragment containing a simple view.
 */
public class ForecastFragment extends Fragment {

    private ArrayAdapter<String> mForecastAdapter;

    public ForecastFragment() {
        setHasOptionsMenu(true);
    }

    @Override
    public void onStart() {
        super.onStart();
        updateWeather();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mForecastAdapter = new ArrayAdapter<>(
                getActivity(),
                R.layout.list_item_forecast,
                R.id.list_item_forecast_textView,
                new ArrayList<String>()
        );

        View rootView = inflater.inflate(R.layout.fragment_forecast, container, false);

        ListView listView = (ListView) rootView.findViewById(R.id.listView_forecast);
        listView.setAdapter(mForecastAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), DetailActivity.class).putExtra(
                        Intent.EXTRA_TEXT, mForecastAdapter.getItem(position)
                );
                startActivity(intent);
            }
        });

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.menu_forecast, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            updateWeather();
            return true;
        }

        return super.onContextItemSelected(item);
    }

    private void updateWeather() {
        String location =
                PreferenceManager.getDefaultSharedPreferences(getActivity()).getString(
                        getString(R.string.pref_location_key),
                        getString(R.string.pref_location_default_value)
                );

        String units =
                PreferenceManager.getDefaultSharedPreferences(getActivity()).getString(
                        getString(R.string.pref_units_key),
                        getString(R.string.pref_units_metric)
                );

        new FetchWeatherTask(mForecastAdapter).execute(location, units);
    }
}