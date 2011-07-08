package com.d.util;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

/** 永続化マネージャー */
public final class PMF {
	private PMF() {
	}

	private static final PersistenceManagerFactory pmfInstance = JDOHelper
			.getPersistenceManagerFactory("transactions-optional");

	public static PersistenceManagerFactory get() {
		return pmfInstance;
	}

}
