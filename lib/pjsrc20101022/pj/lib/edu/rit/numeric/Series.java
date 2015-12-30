//******************************************************************************
//
// File:    Series.java
// Package: edu.rit.numeric
// Unit:    Class edu.rit.numeric.Series
//
// This Java source file is copyright (C) 2010 by Alan Kaminsky. All rights
// reserved. For further information, contact the author, Alan Kaminsky, at
// ark@cs.rit.edu.
//
// This Java source file is part of the Parallel Java Library ("PJ"). PJ is free
// software; you can redistribute it and/or modify it under the terms of the GNU
// General Public License as published by the Free Software Foundation; either
// version 3 of the License, or (at your option) any later version.
//
// PJ is distributed in the hope that it will be useful, but WITHOUT ANY
// WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
// A PARTICULAR PURPOSE. See the GNU General Public License for more details.
//
// A copy of the GNU General Public License is provided in the file gpl.txt. You
// may also obtain a copy of the GNU General Public License on the World Wide
// Web at http://www.gnu.org/licenses/gpl.html.
//
//******************************************************************************

package edu.rit.numeric;

import java.io.PrintStream;
import java.io.PrintWriter;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class Series is the abstract base class for a series of real values (type
 * <TT>double</TT>).
 *
 * @author  Alan Kaminsky
 * @version 11-Oct-2010
 */
public abstract class Series
	implements Iterable<Double>
	{

// Exported helper classes.

	/**
	 * Class Series.Stats holds the mean, variance, and standard deviation of a
	 * {@linkplain Series}.
	 *
	 * @author  Alan Kaminsky
	 * @version 12-Jun-2007
	 */
	public static class Stats
		{
		/**
		 * Mean of the series' X values.
		 */
		public final double meanX;

		/**
		 * Variance of the series' X values.
		 */
		public final double varX;

		/**
		 * Standard deviation of the series' X values.
		 */
		public final double stddevX;

		/**
		 * Construct a new Series.Stats object.
		 */
		private Stats
			(double meanX,
			 double varX,
			 double stddevX)
			{
			this.meanX = meanX;
			this.varX = varX;
			this.stddevX = stddevX;
			}
		}

	/**
	 * Class Series.RobustStats holds the median and mean absolute deviation of
	 * a {@linkplain Series}.
	 *
	 * @author  Alan Kaminsky
	 * @version 11-Oct-2010
	 */
	public static class RobustStats
		{
		/**
		 * Median of the series' X values. Let the length of the series be
		 * <I>n</I>. Let the series' X values be sorted into ascending order. If
		 * <I>n</I> is even, the median is
		 * (<I>x</I><SUB><I>n</I>/2&minus;1</SUB>&nbsp;+&nbsp;<I>x</I><SUB><I>n</I>/2</SUB>)/2.
		 * If <I>n</I> is odd, the median is
		 * <I>x</I><SUB>(<I>n</I>&minus;1)/2</SUB>.
		 */
		public final double medianX;

		/**
		 * Mean absolute deviation of the series' X values. The absolute
		 * deviation of one value <I>x</I> is
		 * |<I>x</I>&nbsp;&minus;&nbsp;<I>medianX</I>|. The mean absolute
		 * deviation is the mean of all the X values' absolute deviations.
		 */
		public final double meanAbsDevX;

		/**
		 * Construct a new Series.RobustStats object.
		 */
		private RobustStats
			(double medianX,
			 double meanAbsDevX)
			{
			this.medianX = medianX;
			this.meanAbsDevX = meanAbsDevX;
			}
		}

// Exported constructors.

	/**
	 * Construct a new series.
	 */
	public Series()
		{
		}

// Exported operations.

	/**
	 * Returns the number of values in this series.
	 *
	 * @return  Length.
	 */
	public abstract int length();

	/**
	 * Determine if this series is empty.
	 *
	 * @return  True if this series is empty (length = 0), false otherwise.
	 */
	public boolean isEmpty()
		{
		return length() == 0;
		}

	/**
	 * Returns the given X value in this series.
	 *
	 * @param  i  Index.
	 *
	 * @return  The X value in this series at index <TT>i</TT>.
	 *
	 * @exception  ArrayIndexOutOfBoundsException
	 *     (unchecked exception) Thrown if <TT>i</TT> is not in the range
	 *     <TT>0</TT> .. <TT>length()-1</TT>.
	 */
	public abstract double x
		(int i);

	/**
	 * Returns the minimum value in this series.
	 *
	 * @return  Minimum X value.
	 */
	public double minX()
		{
		int n = length();
		double result = Double.POSITIVE_INFINITY;
		for (int i = 0; i < n; ++ i)
			{
			result = Math.min (result, x(i));
			}
		return result;
		}

	/**
	 * Returns the maximum value in this series.
	 *
	 * @return  Maximum X value.
	 */
	public double maxX()
		{
		int n = length();
		double result = Double.NEGATIVE_INFINITY;
		for (int i = 0; i < n; ++ i)
			{
			result = Math.max (result, x(i));
			}
		return result;
		}

	/**
	 * Returns a {@linkplain Stats Stats} object containing statistics of this
	 * series.
	 *
	 * @return  Statistics.
	 */
	public Stats stats()
		{
		int n = length();
		if (n == 0) return new Stats (0.0, 0.0, 0.0);
		double sumX = 0.0;
		for (int i = 0; i < n; ++ i)
			{
			sumX += x(i);
			}
		double meanX = sumX / n;
		double sumdevX = 0.0;
		double sumdevsqrX = 0.0;
		for (int i = 0; i < n; ++ i)
			{
			double devX = x(i) - meanX;
			double devsqrX = devX * devX;
			sumdevX += devX;
			sumdevsqrX += devsqrX;
			}
		double varX =
			n == 1 ? 0.0 : (sumdevsqrX - sumdevX * sumdevX / n) / (n - 1);
		double stddevX = Math.sqrt (varX);
		return new Stats (meanX, varX, stddevX);
		}

	/**
	 * Returns a {@linkplain RobustStats RobustStats} object containing robust
	 * statistics of this series.
	 *
	 * @return  Robust statistics.
	 */
	public RobustStats robustStats()
		{
		int n = length();
		if (n == 0) return new RobustStats (0.0, 0.0);
		double[] sortedX = new double [n];
		for (int i = 0; i < n; ++ i) sortedX[i] = x(i);
		Arrays.sort (sortedX);
		double medianX =
			(n & 1) == 0 ?
				0.5*(sortedX[(n >> 1) - 1] + sortedX[n >> 1]) :
				sortedX[(n - 1) >> 1];
		double sumAbsDevX = 0.0;
		for (int i = 0; i < n; ++ i)
			{
			sumAbsDevX += Math.abs (sortedX[i] - medianX);
			}
		return new RobustStats (medianX, sumAbsDevX/n);
		}

	/**
	 * Returns an iterator over the values in this series.
	 *
	 * @return  Iterator.
	 */
	public Iterator<Double> iterator()
		{
		return new Iterator<Double>()
			{
			int i = 0;

			public boolean hasNext()
				{
				return i < length();
				}

			public Double next()
				{
				try
					{
					return x (i ++);
					}
				catch (ArrayIndexOutOfBoundsException exc)
					{
					throw new NoSuchElementException();
					}
				}

			public void remove()
				{
				throw new UnsupportedOperationException();
				}
			};
		}

	/**
	 * Print this series on the standard output. Each line of output consists of
	 * the index and the value, separated by a tab.
	 */
	public void print()
		{
		print (System.out);
		}

	/**
	 * Print this series on the given print stream. Each line of output consists
	 * of the index and the value, separated by a tab.
	 *
	 * @param  theStream  Print stream.
	 */
	public void print
		(PrintStream theStream)
		{
		int n = length();
		for (int i = 0; i < n; ++ i)
			{
			theStream.print (i);
			theStream.print ('\t');
			theStream.println (x(i));
			}
		}

	/**
	 * Print this series on the given print writer. Each line of output consists
	 * of the index and the value, separated by a tab.
	 *
	 * @param  theWriter  Print writer.
	 */
	public void print
		(PrintWriter theWriter)
		{
		int n = length();
		for (int i = 0; i < n; ++ i)
			{
			theWriter.print (i);
			theWriter.print ('\t');
			theWriter.println (x(i));
			}
		}

	}
