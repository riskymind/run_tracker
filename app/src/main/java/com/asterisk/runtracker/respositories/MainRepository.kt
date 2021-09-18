package com.asterisk.runtracker.respositories

import com.asterisk.runtracker.db.Run
import com.asterisk.runtracker.db.RunDao
import javax.inject.Inject

class MainRepository @Inject constructor(
    val runDao: RunDao
) {

    suspend fun  insertRun(run: Run) = runDao.insertRun(run)
    suspend fun  deleteRun(run: Run) = runDao.deleteRun(run)

    fun getAllRunSortedByDate() = runDao.getAllRunsSortedByDate()
    fun getAllRunSortedByDistance() = runDao.getAllRunsSortedByDistance()
    fun getAllRunSortedByCaloriesBurned() = runDao.getAllRunsSortedByCaloriesBurned()
    fun getAllRunSortedByTimeInMillis() = runDao.getAllRunsSortedByTimeInMillis()
    fun getAllRunSortedByAvgSpeed() = runDao.getAllRunsSortedByAvgSpeed()


    fun getTotalAvgSpeed() = runDao.getTotalAvgSpeed()
    fun getTotalCaloriesBurned() = runDao.getTotalCaloriesBurned()
    fun getTotalDistance() = runDao.getTotalDistance()
    fun getTotalTimeInMillis() = runDao.getTotalTimeInMills()

}