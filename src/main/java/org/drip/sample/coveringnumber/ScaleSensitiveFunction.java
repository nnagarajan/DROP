
package org.drip.sample.coveringnumber;

import org.drip.function.definition.R1ToR1;
import org.drip.numerical.common.FormatUtil;
import org.drip.service.env.EnvManager;
import org.drip.spaces.cover.ScaleSensitiveCoveringBounds;

/*
 * -*- mode: java; tab-width: 4; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 */

/*!
 * Copyright (C) 2020 Lakshmi Krishnamurthy
 * Copyright (C) 2019 Lakshmi Krishnamurthy
 * Copyright (C) 2018 Lakshmi Krishnamurthy
 * Copyright (C) 2017 Lakshmi Krishnamurthy
 * Copyright (C) 2016 Lakshmi Krishnamurthy
 * Copyright (C) 2015 Lakshmi Krishnamurthy
 * 
 *  This file is part of DROP, an open-source library targeting analytics/risk, transaction cost analytics,
 *  	asset liability management analytics, capital, exposure, and margin analytics, valuation adjustment
 *  	analytics, and portfolio construction analytics within and across fixed income, credit, commodity,
 *  	equity, FX, and structured products. It also includes auxiliary libraries for algorithm support,
 *  	numerical analysis, numerical optimization, spline builder, model validation, statistical learning,
 *  	and computational support.
 *  
 *  	https://lakshmidrip.github.io/DROP/
 *  
 *  DROP is composed of three modules:
 *  
 *  - DROP Product Core - https://lakshmidrip.github.io/DROP-Product-Core/
 *  - DROP Portfolio Core - https://lakshmidrip.github.io/DROP-Portfolio-Core/
 *  - DROP Computational Core - https://lakshmidrip.github.io/DROP-Computational-Core/
 * 
 * 	DROP Product Core implements libraries for the following:
 * 	- Fixed Income Analytics
 * 	- Loan Analytics
 * 	- Transaction Cost Analytics
 * 
 * 	DROP Portfolio Core implements libraries for the following:
 * 	- Asset Allocation Analytics
 *  - Asset Liability Management Analytics
 * 	- Capital Estimation Analytics
 * 	- Exposure Analytics
 * 	- Margin Analytics
 * 	- XVA Analytics
 * 
 * 	DROP Computational Core implements libraries for the following:
 * 	- Algorithm Support
 * 	- Computation Support
 * 	- Function Analysis
 *  - Model Validation
 * 	- Numerical Analysis
 * 	- Numerical Optimizer
 * 	- Spline Builder
 *  - Statistical Learning
 * 
 * 	Documentation for DROP is Spread Over:
 * 
 * 	- Main                     => https://lakshmidrip.github.io/DROP/
 * 	- Wiki                     => https://github.com/lakshmiDRIP/DROP/wiki
 * 	- GitHub                   => https://github.com/lakshmiDRIP/DROP
 * 	- Repo Layout Taxonomy     => https://github.com/lakshmiDRIP/DROP/blob/master/Taxonomy.md
 * 	- Javadoc                  => https://lakshmidrip.github.io/DROP/Javadoc/index.html
 * 	- Technical Specifications => https://github.com/lakshmiDRIP/DROP/tree/master/Docs/Internal
 * 	- Release Versions         => https://lakshmidrip.github.io/DROP/version.html
 * 	- Community Credits        => https://lakshmidrip.github.io/DROP/credits.html
 * 	- Issues Catalog           => https://github.com/lakshmiDRIP/DROP/issues
 * 	- JUnit                    => https://lakshmidrip.github.io/DROP/junit/index.html
 * 	- Jacoco                   => https://lakshmidrip.github.io/DROP/jacoco/index.html
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *   	you may not use this file except in compliance with the License.
 *   
 *  You may obtain a copy of the License at
 *  	http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  	distributed under the License is distributed on an "AS IS" BASIS,
 *  	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  
 *  See the License for the specific language governing permissions and
 *  	limitations under the License.
 */

/**
 * <i>ScaleSensitiveFunction</i> demonstrates Computation of the Restricted Covers, Restricted Probability
 * Bounds, the Lower Bounds, and the Upper Bounds for Functions that are absolutely Bounded.
 *  
 * <br><br>
 *  <ul>
 *		<li><b>Module </b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/ComputationalCore.md">Computational Core Module</a></li>
 *		<li><b>Library</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/StatisticalLearningLibrary.md">Statistical Learning</a></li>
 *		<li><b>Project</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/sample/README.md">DROP API Construction and Usage</a></li>
 *		<li><b>Package</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/sample/coveringnumber/README.md">R<sup>d</sup>Agnostic Function Covering Number Bounds</a></li>
 *  </ul>
 * <br><br>
 *
 * @author Lakshmi Krishnamurthy
 */

public class ScaleSensitiveFunction {

	public static final void main (
		final String[] astrArgs)
		throws Exception
	{
		EnvManager.InitEnv ("");

		final int iSampleSize = 10;

		R1ToR1 auFatShatter = new R1ToR1 (null) {
			@Override public double evaluate (
				final double dblX)
				throws Exception
			{
				return iSampleSize;
			}
		};

		ScaleSensitiveCoveringBounds sscn = new ScaleSensitiveCoveringBounds (
			auFatShatter,
			iSampleSize
		);

		double[] adblCover = new double[] {
			500., 600., 700., 800., 900., 960.
		};

		System.out.println ("\n\t||------------------------------------------------||");

		System.out.println ("\t||    Scale    Sensitive    Covering   Numbers    ||");

		System.out.println ("\t||    -----    ---------    --------   -------    ||");

		System.out.println ("\t|| L -> R:                                        ||");

		System.out.println ("\t||   Sample Size Lower Bound                      ||");

		System.out.println ("\t||   Restricted Subset Cardinality                ||");

		System.out.println ("\t||   Probability of the Cover Weight Upper Bound  ||");

		System.out.println ("\t||   Log Log Covering Number Lower Bound          ||");

		System.out.println ("\t||   Log Log Covering Number Upper Bound          ||");

		System.out.println ("\t||------------------------------------------------||");

		for (double dblCover : adblCover)
			System.out.println ("\t|| [" + FormatUtil.FormatDouble (dblCover, 3, 0, 1.) + "] => " +
				FormatUtil.FormatDouble (sscn.sampleSizeLowerBound (dblCover), 1, 1, 1.) + " |" +
				FormatUtil.FormatDouble (sscn.restrictedSubsetCardinality (dblCover), 3, 0, 1.) + " | " +
				FormatUtil.FormatDouble (sscn.upperProbabilityBoundWeight (dblCover), 5, 0, 1.) + " | " +
				FormatUtil.FormatDouble (Math.log (sscn.logLowerBound (dblCover)), 1, 2, 1.) + " -> " +
				FormatUtil.FormatDouble (Math.log (sscn.logUpperBound (dblCover)), 1, 2, 1.) + " ||"
			);

		System.out.println ("\t||------------------------------------------------||");

		EnvManager.TerminateEnv();
	}
}
