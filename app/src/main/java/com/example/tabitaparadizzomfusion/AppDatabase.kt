package com.example.tabitaparadizzomfusion
/*
Tabita - 19551
Marcelle - 19534
*/

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.tabitaparadizzomfusion.daos.*
import com.example.tabitaparadizzomfusion.models.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.concurrent.ThreadLocalRandom

@Database(
    entities = [User::class, Basket::class, BasketItem::class, Category::class, Meal::class, MealStep::class, MealOption::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun categoryDao(): CategoryDao
    abstract fun mealDao(): MealDao
    abstract fun basketDao(): BasketDao
    abstract fun basketItemDao(): BasketItemDao

    private class AppDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            INSTANCE?.let { database ->
                scope.launch {
                    createBasket(database.basketDao())
                    populateCategories(database.categoryDao())
                    populateMeals(database.mealDao())
                }
            }
        }

        suspend fun createBasket(basketDao: BasketDao) {
            basketDao.deleteAll();

            var basket = Basket(1)
            basketDao.insertWithTimestamp(basket)
        }

        suspend fun populateCategories(categoryDao: CategoryDao) {
            // Delete all categories.
            categoryDao.deleteAll()

            // Add sample categories.
            var category = Category(1, "Meal Category 1")
            categoryDao.insert(category)

            category = Category(2, "Meal Category 2")
            categoryDao.insert(category)

            category = Category(3, "Meal Category 3")
            categoryDao.insert(category)
        }

        suspend fun populateMeals(mealDao: MealDao) {
            mealDao.deleteAll();

            repeat(30) { index ->
                val categoryId: Long = (1L..3L).random()
                var price: Double = ThreadLocalRandom.current().nextDouble(51.3257, 52.4557);
                var meal = Meal(
                    index.toLong(),
                    categoryId,
                    "Meal $index",
                    price,
                    "Description Meal $index"
                );
                mealDao.insert(meal)
            }
        }
    }

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): AppDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "main_database"
                )
                    .addCallback(AppDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}