package com.helloworld.presentation.ui.overview

import android.graphics.Color
import android.view.View
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.FragmentActivity
import com.github.barteksc.pdfviewer.PDFView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.helloworld.R
import com.helloworld.util.localization.LocalizationProvider
import com.helloworld.util.localization.setLanguageApp
import com.helloworld.presentation.ui.theme.themeing.abstract_factory.ThemeFactoryProvider
import com.helloworld.presentation.ui.theme.themeing.setThemeApp
import com.helloworld.presentation.ui.theme.themeing.setThemeDynamicApp
import com.helloworld.presentation.ui.theme.themeing.updateFontSize
import com.helloworld.presentation.widget.HighlightMultipleWords
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.io.InputStream
import java.net.URL
import kotlin.random.Random


@Composable
fun OverviewScreen(navigateToProfile: () -> Unit) {
    var chart: LineChart = LineChart(LocalContext.current)
    val lineData = LineData()
//    lineData.addDataSet()
    chart.getDescription().setEnabled(false);
    chart.setTouchEnabled(true);
//    chart.setOnChartValueSelectedListener(LocalContext);

    chart.setDrawGridBackground(false);

//    val mv: MyMarkerView = MyMarkerView(this, android.R.layout.custom_marker_view)

    // Set the marker to the chart
//    mv.setChartView(chart);
//    chart.setMarker(mv);

    // enable scaling and dragging
    chart.setDragEnabled(true);
    chart.setScaleEnabled(true);
    // chart.setScaleXEnabled(true);
    // chart.setScaleYEnabled(true);

    // force pinch zoom along both axis
    chart.setPinchZoom(true);

    var xAxis: XAxis
    {   // // X-Axis Style // //
        xAxis = chart.xAxis

        // vertical grid lines
        xAxis.enableGridDashedLine(10f, 10f, 0f)
    }

    var yAxis: YAxis
    {   // // Y-Axis Style // //
        yAxis = chart.axisLeft

        // disable dual axis (only use LEFT axis)
        chart.axisRight.isEnabled = false

        // horizontal grid lines
        yAxis.enableGridDashedLine(10f, 10f, 0f)

        // axis range
        yAxis.axisMaximum = 200f
        yAxis.axisMinimum = -50f
    }


    val context = LocalContext.current
    val activity = context as FragmentActivity

    fun generateLineData(): LineData {
        val d = LineData()
        val entries = mutableListOf<Entry>()
        val count = 12

        for (index in 0 until count) {
            entries.add(index, Entry(index + 0.5f, Random.nextFloat()));
        }
        val set: LineDataSet = LineDataSet(entries, "Line DataSet");


        set.setColor(Color.rgb(240, 238, 70));
        set.setLineWidth(2.5f);
        set.setCircleColor(Color.rgb(240, 238, 70));
        set.setCircleRadius(5f);
        set.setFillColor(Color.rgb(240, 238, 70));
        set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set.setDrawValues(true);
        set.setValueTextSize(10f);
        set.setValueTextColor(Color.rgb(240, 238, 70));

        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        d.addDataSet(set);

        return d;
    }


    fun getRandom(range: Int, start: Int): Float {
        return (Math.random() * range).toFloat() + start
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("change theme large",
            style = ThemeFactoryProvider.fontFactory.largeTitle,
            color = ThemeFactoryProvider.colorFactory.bottomNavBackgroundColor,
            modifier = Modifier
                .clickable {
                    setThemeApp(com.helloworld.presentation.ui.theme.themeing.AppTheme.Dark)
                    updateFontSize(10)
                }


        )

        Text("change theme medium",
            style = ThemeFactoryProvider.fontFactory.largeTitle,
            color = ThemeFactoryProvider.colorFactory.bottomNavBackgroundColor,
            modifier = Modifier
                .clickable {
                    setThemeApp(com.helloworld.presentation.ui.theme.themeing.AppTheme.Dark)
                    updateFontSize(0)
                }


        )

        Text("change theme small",
            style = ThemeFactoryProvider.fontFactory.largeTitle,
            color = ThemeFactoryProvider.colorFactory.bottomNavBackgroundColor,
            modifier = Modifier
                .clickable {
                    setThemeApp(com.helloworld.presentation.ui.theme.themeing.AppTheme.Dark)
                    updateFontSize(-10)
                }


        )



        Text(
            "change theme dynamic dark",
            color = ThemeFactoryProvider.colorFactory.bottomNavBackgroundColor,
            modifier = Modifier.clickable {
                setThemeDynamicApp()
            })
        Button(onClick = {
            navigateToProfile()
        }) {
            Text(text = "ProfileScreen")
        }
        HighlightMultipleWords(
            text = "Hello Jetpack Compose",
            wordsToHighlight = listOf("Jetpack", "Compose"),
            colors = listOf(androidx.compose.ui.graphics.Color.Red, androidx.compose.ui.graphics.Color.Blue)
        )

        AndroidView(
            modifier = Modifier
                .height(300.dp)
                .width(400.dp),
            factory = {
                View.inflate(it, com.helloworld.R.layout.extra_layout, null)
            },
            update = {


                val mChart = it.findViewById<LineChart>(R.id.chart1)
                val d = LineData()
                val entries = ArrayList<Entry>()

                for (index in 0 until 20) {
                    entries.add(Entry(index + 0.5f, getRandom(15, 5).toFloat()))
                }
                val set1 = LineDataSet(entries, "")
//                lineDataSet.setDrawCircles(false)
//                lineDataSet.setDrawValues(false)
                set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
                set1.setCubicIntensity(0.2f);
                set1.setDrawFilled(true);
                set1.setDrawCircles(false);
                set1.setLineWidth(1.8f);
                set1.setCircleRadius(4f);
                set1.setCircleColor(Color.WHITE);
                set1.setHighLightColor(Color.rgb(244, 117, 117));
                set1.setColor(Color.WHITE);
                set1.setFillColor(Color.WHITE);
                set1.setFillAlpha(100);
                set1.setDrawHorizontalHighlightIndicator(false);

                val data: LineData = LineData(set1)
                data.setValueTextSize(9f);
                data.setDrawValues(false);

                mChart.data = (data)

//                chart.data
            }
        )
        PdfView()
    }
}

@Composable
fun PdfView() {
    AndroidView(
        modifier = Modifier
            .height(300.dp)
            .width(400.dp),
        factory = {
            View.inflate(it, com.helloworld.R.layout.pdf_layout, null)
        },
        update = {
            val pdfView = it.findViewById<PDFView>(R.id.pdfView)
            val urlString = "http://samples.leanpub.com/thereactnativebook-sample.pdf"
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val input: InputStream = URL(urlString).openStream()
                    withContext(Dispatchers.Main) {
                        pdfView.fromStream(input).load()

                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    )
}
