
package org.drip.sample.gammaincomplete;

import org.drip.service.common.FormatUtil;
import org.drip.service.env.EnvManager;
import org.drip.specialfunction.incompletegamma.UpperRegularized;

/*
 * -*- mode: java; tab-width: 4; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 */

/*!
 * Copyright (C) 2020 Lakshmi Krishnamurthy
 * Copyright (C) 2019 Lakshmi Krishnamurthy
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
 * <i>UpperRegularizedEstimate</i> illustrates the Estimation of the Regularized Upper Incomplete Gamma
 * 	Function using several Techniques. The References are:
 * 
 * <br><br>
 * 	<ul>
 * 		<li>
 * 			Geddes, K. O., M. L. Glasser, R. A. Moore, and T. C. Scott (1990): Evaluation of Classes of
 * 				Definite Integrals involving Elementary Functions via Differentiation of Special Functions
 * 				<i>Applicable Algebra in Engineering, Communications, and </i> <b>1 (2)</b> 149-165
 * 		</li>
 * 		<li>
 * 			Gradshteyn, I. S., I. M. Ryzhik, Y. V. Geronimus, M. Y. Tseytlin, and A. Jeffrey (2015):
 * 				<i>Tables of Integrals, Series, and Products</i> <b>Academic Press</b>
 * 		</li>
 * 		<li>
 * 			Mathar, R. J. (2010): Numerical Evaluation of the Oscillatory Integral over
 *				e<sup>iπx</sup> x<sup>(1/x)</sup> between 1 and ∞
 *				https://arxiv.org/pdf/0912.3844.pdf <b>arXiV</b>
 * 		</li>
 * 		<li>
 * 			National Institute of Standards and Technology (2019a): Incomplete Gamma and Related Functions
 * 				https://dlmf.nist.gov/8
 * 		</li>
 * 		<li>
 * 			Wikipedia (2019): Incomplete Gamma Function
 * 				https://en.wikipedia.org/wiki/Incomplete_gamma_function
 * 		</li>
 * 	</ul>
 *
 *	<br><br>
 *  <ul>
 *		<li><b>Module </b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/ComputationalCore.md">Computational Core Module</a></li>
 *		<li><b>Library</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/NumericalAnalysisLibrary.md">Numerical Analysis Library</a></li>
 *		<li><b>Project</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/sample/README.md">DROP API Construction and Usage</a></li>
 *		<li><b>Package</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/sample/gammaincomplete/README.md">Estimates of Incomplete Gamma Functions</a></li>
 *  </ul>
 *
 * @author Lakshmi Krishnamurthy
 */

public class UpperRegularizedEstimate
{

	private static final void PrintSequence (
		final double s,
		final double[] zArray,
		final UpperRegularized gaussContinuedFraction,
		final UpperRegularized abramowitzStegun,
		final UpperRegularized eulerIntegral,
		final UpperRegularized weisstein)
		throws Exception
	{
		System.out.println ("\t|-------------------------------------------------------------||");

		System.out.println ("\t|    REGULARIZED UPPER INCOMPLETE GAMMA FUNCTION ESTIMATES    ||");

		System.out.println ("\t|                        s => " + FormatUtil.FormatDouble (s, 1, 1, 1.));

		System.out.println ("\t|-------------------------------------------------------------||");

		System.out.println ("\t|        L -> R:                                              ||");

		System.out.println ("\t|                - Gauss Continued Fraction                   ||");

		System.out.println ("\t|                - Abramowitz Stegun (2007)                   ||");

		System.out.println ("\t|                - Euler Integral                             ||");

		System.out.println ("\t|                - Weisstein                                  ||");

		System.out.println ("\t|-------------------------------------------------------------||");

		for (double z : zArray)
		{
			System.out.println (
				"\t|" + FormatUtil.FormatDouble (z, 2, 2, 1.) + " => " +
				FormatUtil.FormatDouble (
					gaussContinuedFraction.p (
						s,
						z
					), 1, 8, 1.
				) + " |" +
				FormatUtil.FormatDouble (
					abramowitzStegun.p (
						s,
						z
					), 1, 8, 1.
				) + " |" +
				FormatUtil.FormatDouble (
					eulerIntegral.p (
						s,
						z
					), 1, 8, 1.
				) + " |" +
				FormatUtil.FormatDouble (
					weisstein.p (
						s,
						z
					), 1, 8, 1.
				) + " ||"
			);
		}

		System.out.println ("\t|-------------------------------------------------------------||");
	}

	public static final void main (
		final String[] argumentArray)
		throws Exception
	{
		EnvManager.InitEnv ("");

		int n = 30;
		double[] sArray =
		{
			2.0,
			3.0,
			4.0,
			5.0,
			6.0,
			7.0,
			8.0,
			9.0,
		};
		double[] zArray =
		{
			 0.01,
			 0.05,
			 0.10,
			 0.25,
			 0.50,
			 0.75,
			 1.00,
			 1.25,
			 1.50,
			 1.75,
			 2.00,
			 3.00,
			 4.00,
			 5.00,
			 6.00,
			 8.00,
			10.00,
			12.00,
			15.00,
			20.00,
		};

		UpperRegularized weisstein = UpperRegularized.Weisstein();

		UpperRegularized eulerIntegral = UpperRegularized.EulerIntegral();

		UpperRegularized abramowitzStegun = UpperRegularized.AbramowitzStegun2007 (n);

		UpperRegularized gaussContinuedFraction = UpperRegularized.GaussContinuedFraction (n);

		for (double s : sArray)
		{
			PrintSequence (
				s,
				zArray,
				gaussContinuedFraction,
				abramowitzStegun,
				eulerIntegral,
				weisstein
			);
		}

		EnvManager.TerminateEnv();
	}
}
