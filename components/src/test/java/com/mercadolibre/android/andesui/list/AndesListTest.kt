package com.mercadolibre.android.andesui.list

import android.os.Build
import android.view.View
import com.mercadolibre.android.andesui.BuildConfig
import com.mercadolibre.android.andesui.button.size.AndesLargeButtonSize
import com.mercadolibre.android.andesui.list.size.AndesListViewItemSize
import com.mercadolibre.android.andesui.list.size.AndesListViewItemSmallSize
import com.mercadolibre.android.andesui.list.type.AndesListType
import com.mercadolibre.android.andesui.list.utils.AndesListDelegate
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.LOLLIPOP])
class AndesListTest {
    private var context = RuntimeEnvironment.application

    companion object {
        private const val SIZE = 10
    }

    @Test
    fun `test delegate`() {
        val item = AndesListViewItemSimple(context, "test")
        val view = View(context)
        val list = AndesList(context)

        val andesListDelegate = object : AndesListDelegate {
            override fun onItemClick(andesList: AndesList, position: Int) {
                Assert.assertEquals(0, position)
            }

            override fun bind(andesList: AndesList, view: View, position: Int): AndesListViewItem {
                return item
            }

            override fun getDataSetSize(andesList: AndesList): Int = SIZE

        }

        andesListDelegate.onItemClick(list, 0)
        Assert.assertEquals(SIZE, andesListDelegate.getDataSetSize(list))
        Assert.assertEquals(item, andesListDelegate.bind(list, view, 0))
    }

    @Test
    fun `test list with different sizes`() {
        var list = AndesList(context, AndesListViewItemSize.SMALL, AndesListType.SIMPLE)
        Assert.assertEquals(list.size, AndesListViewItemSize.SMALL)

        list = AndesList(context, AndesListViewItemSize.MEDIUM, AndesListType.SIMPLE)
        Assert.assertEquals(list.size, AndesListViewItemSize.MEDIUM)

        list = AndesList(context, AndesListViewItemSize.LARGE, AndesListType.SIMPLE)
        Assert.assertEquals(list.size, AndesListViewItemSize.LARGE)
    }

    @Test
    fun `test list with different item types`() {
        var list = AndesList(context, AndesListViewItemSize.MEDIUM, AndesListType.SIMPLE)
        Assert.assertEquals(list.type, AndesListType.SIMPLE)

        list = AndesList(context, AndesListViewItemSize.MEDIUM, AndesListType.CHEVRON)
        Assert.assertEquals(list.type, AndesListType.CHEVRON)

        list = AndesList(context, AndesListViewItemSize.MEDIUM, AndesListType.CHECK_BOX)
        Assert.assertEquals(list.type, AndesListType.CHECK_BOX)

        list = AndesList(context, AndesListViewItemSize.MEDIUM, AndesListType.RADIO_BUTTON)
        Assert.assertEquals(list.type, AndesListType.RADIO_BUTTON)
    }


    @Test
    fun `test list item selection`() {

        val list = AndesList(context, AndesListViewItemSize.MEDIUM, AndesListType.SIMPLE)
        val item = AndesListViewItemSimple(context, "test")
        val listItems = ArrayList<AndesListViewItem>()

        for (i in 1..SIZE) {
            listItems.add(item)
        }

        listItems[5].itemSelected = true

        val andesListDelegate = object : AndesListDelegate {
            override fun onItemClick(andesList: AndesList, position: Int) {
                Assert.assertEquals(true, listItems[position].itemSelected)
            }

            override fun bind(andesList: AndesList, view: View, position: Int): AndesListViewItem {
                return item
            }

            override fun getDataSetSize(andesList: AndesList): Int = SIZE

        }

        list.delegate = andesListDelegate
        andesListDelegate.onItemClick(list, 5)
    }

}