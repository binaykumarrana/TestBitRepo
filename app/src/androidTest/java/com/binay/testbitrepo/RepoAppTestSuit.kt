package com.binay.testbitrepo

import org.junit.runner.RunWith
import org.junit.runners.Suite

/**

 * Created by Binay on 23/5/21.
 */
@RunWith(Suite::class)
@Suite.SuiteClasses(
    ExampleInstrumentedTest::class,
    AppDatabaseTest::class,
    RepoDaoTest::class
)
class RepoAppTestSuit {
}