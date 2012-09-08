/**
 * Copyright (C) 2009, 2010 SC 4ViewSoft SRL
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mad.lou.chart;

import java.text.DateFormat;
import java.util.Date;

import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;


public class FoodChart{
  /**
   * Returns the chart name.
   * 
   * @return the chart name
   */
  String currentDateTimeString = DateFormat.getDateInstance().format(new Date());
  
 public Intent execute(Context context, double fruit, double veg, double dairy, double meat, double grain) {	  
	 
	 Log.v("FOOD_CHART", "" + fruit+veg+dairy+meat+grain);
	
	int[] colors = new int[] {Color.GREEN, Color.MAGENTA, Color.YELLOW, Color.CYAN , Color.LTGRAY};
    DefaultRenderer renderer = buildCategoryRenderer(colors);
    renderer.setZoomButtonsVisible(true);
    renderer.setZoomEnabled(true);
    CategorySeries categorySeries = new CategorySeries("Food Chart");
    categorySeries.add("fruit ", fruit);
    categorySeries.add("veg", veg);
    categorySeries.add("dairy", dairy);
    categorySeries.add("meat", meat);
    categorySeries.add("grain", grain);
    return ChartFactory.getPieChartIntent(context, categorySeries, renderer, currentDateTimeString);
     
  }
  
	  protected DefaultRenderer buildCategoryRenderer(int[] colors) {
	    DefaultRenderer renderer = new DefaultRenderer();
	    renderer.setLabelsTextSize(15);
	    renderer.setLegendTextSize(15);
	    renderer.setMargins(new int[] { 30, 50, 15, 0, -15 });
	    for (int color : colors) {
	      SimpleSeriesRenderer r = new SimpleSeriesRenderer();
	      r.setColor(color);
	      renderer.addSeriesRenderer(r);
	    }
	    return renderer;
	  }

}
