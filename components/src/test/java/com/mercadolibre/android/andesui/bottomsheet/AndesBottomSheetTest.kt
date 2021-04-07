package com.mercadolibre.android.andesui.bottomsheet

import android.os.Build
import com.google.android.material.bottomsheet.BottomSheetBehavior
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.mercadolibre.android.andesui.bottomsheet.state.AndesBottomSheetState
import com.mercadolibre.android.andesui.bottomsheet.title.AndesBottomSheetTitleAlignment
import com.nhaarman.mockitokotlin2.anyOrNull
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.Mockito.never
import org.mockito.Mockito.any
import org.mockito.internal.util.reflection.FieldSetter
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.LOLLIPOP])
class AndesBottomSheetTest {
    private var context = RuntimeEnvironment.application
    private lateinit var andesBottomSheet: AndesBottomSheet

    @Before
    fun setUp() {
        andesBottomSheet = AndesBottomSheet(context)
    }

    @Test
    fun `only context constructor`() {
        assertEquals(andesBottomSheet.peekHeight, DEFAULT_PEEK_HEIGHT)
        assertEquals(andesBottomSheet.state, DEFAULT_BOTTOM_SHEET_STATE)
        assertEquals(andesBottomSheet.titleText, null)
        assertEquals(andesBottomSheet.titleAlignment, AndesBottomSheetTitleAlignment.CENTERED)
    }

    @Test
    fun `params constructor`() {
        val peekHeight = 250
        val title = "title"

        andesBottomSheet = AndesBottomSheet(
                context,
                peekHeight,
                AndesBottomSheetState.EXPANDED,
                title,
                AndesBottomSheetTitleAlignment.CENTERED
        )

        assertEquals(andesBottomSheet.peekHeight, peekHeight)
        assertEquals(andesBottomSheet.state, AndesBottomSheetState.EXPANDED)
        assertEquals(andesBottomSheet.titleText, title)
        assertEquals(andesBottomSheet.titleAlignment, AndesBottomSheetTitleAlignment.CENTERED)
    }

    @Test
    fun `set fragment should set fragment`() {
        val mockFragment = mock(Fragment::class.java)
        val mockFragmentManager = mockFragmentManager()

        andesBottomSheet.setContent(mockFragmentManager, mockFragment)

        verify(mockFragmentManager).beginTransaction()
    }

    @Test
    fun `set View should call addView on Frame Layout`() {
        val mockFrameLayout = mock(FrameLayout::class.java)
        val mockView = mock(View::class.java)
        FieldSetter.setField(andesBottomSheet, andesBottomSheet::class.java.getDeclaredField("frameView"), mockFrameLayout)

        andesBottomSheet.setContent(mockView)

        verify(mockFrameLayout).addView(mockView)
    }

    @Test
    fun `removeViews should not call removeAllViews on frameView if no views are attached`() {
        val mockFrameLayout = mock(FrameLayout::class.java)
        FieldSetter.setField(andesBottomSheet, andesBottomSheet::class.java.getDeclaredField("frameView"), mockFrameLayout)
        `when`(mockFrameLayout.childCount).thenReturn(0)

        andesBottomSheet.removeContent()

        verify(mockFrameLayout, never()).removeAllViews()
    }

    @Test
    fun `removeViews should call removeAllViews on frameView if views are attached`() {
        val mockFrameLayout = mock(FrameLayout::class.java)
        FieldSetter.setField(andesBottomSheet, andesBottomSheet::class.java.getDeclaredField("frameView"), mockFrameLayout)
        `when`(mockFrameLayout.childCount).thenReturn(1)

        andesBottomSheet.removeContent()

        verify(mockFrameLayout).removeAllViews()
    }

    @Test
    fun `expand method should set state to expanded`() {
        andesBottomSheet.state = AndesBottomSheetState.COLLAPSED

        andesBottomSheet.expand()

        assertEquals(andesBottomSheet.state, AndesBottomSheetState.EXPANDED)
    }

    @Test
    fun `expand method should set state to expanded even if already expanded`() {
        andesBottomSheet.state = AndesBottomSheetState.EXPANDED

        andesBottomSheet.expand()

        assertEquals(andesBottomSheet.state, AndesBottomSheetState.EXPANDED)
    }

    @Test
    fun `collapse method should set state to collapsed`() {
        andesBottomSheet.state = AndesBottomSheetState.EXPANDED

        andesBottomSheet.collapse()

        assertEquals(andesBottomSheet.state, AndesBottomSheetState.COLLAPSED)
    }

    @Test
    fun `collapse method should set state to collapsed even if already collapsed`() {
        andesBottomSheet.state = AndesBottomSheetState.COLLAPSED

        andesBottomSheet.collapse()

        assertEquals(andesBottomSheet.state, AndesBottomSheetState.COLLAPSED)
    }

    @Test
    fun `titleText is not null or empty should show title`() {
        val mockTextView = mock(TextView::class.java)
        FieldSetter.setField(andesBottomSheet, andesBottomSheet::class.java.getDeclaredField("titleTextView"), mockTextView)
        val text = "title"

        andesBottomSheet.titleText = text

        verify(mockTextView).setVisibility(View.VISIBLE)
        verify(mockTextView).setText(text)
    }

    @Test
    fun `titleText is null should not show title`() {
        val mockTextView = mock(TextView::class.java)
        FieldSetter.setField(andesBottomSheet, andesBottomSheet::class.java.getDeclaredField("titleTextView"), mockTextView)
        andesBottomSheet.titleText = null

        verify(mockTextView).setVisibility(View.GONE)
    }

    @Test
    fun `titleText is null or empty should not show title`() {
        val mockTextView = mock(TextView::class.java)
        FieldSetter.setField(andesBottomSheet, andesBottomSheet::class.java.getDeclaredField("titleTextView"), mockTextView)
        andesBottomSheet.titleText = ""

        verify(mockTextView).setVisibility(View.GONE)
    }

    @Test
    fun `title alignment center should show it centered`() {
        val mockTextView = mock(TextView::class.java)
        FieldSetter.setField(andesBottomSheet, andesBottomSheet::class.java.getDeclaredField("titleTextView"), mockTextView)
        andesBottomSheet.titleText = "title"
        andesBottomSheet.titleAlignment = AndesBottomSheetTitleAlignment.CENTERED

        verify(mockTextView).setGravity(Gravity.CENTER)
    }

    @Test
    fun `title alignment left should show it left`() {
        val mockTextView = mock(TextView::class.java)
        FieldSetter.setField(andesBottomSheet, andesBottomSheet::class.java.getDeclaredField("titleTextView"), mockTextView)
        andesBottomSheet.titleText = "title"
        andesBottomSheet.titleAlignment = AndesBottomSheetTitleAlignment.LEFT_ALIGN

        verify(mockTextView).setGravity(Gravity.START)
    }

    @Test
    fun `when  state expanded should show background dim`() {
        val mockView = mock(View::class.java)
        FieldSetter.setField(andesBottomSheet, andesBottomSheet::class.java.getDeclaredField("backgroundDimView"), mockView)

        andesBottomSheet.expand()

        verify(mockView, never()).setVisibility(View.VISIBLE)
    }

    @Test
    fun `when set peek height then should update bottom sheet behavior peek height`() {
        val mockBottomSheetBehavior = mock(BottomSheetBehavior::class.java)
        val newPeekHeight = 300
        FieldSetter.setField(andesBottomSheet, andesBottomSheet::class.java.getDeclaredField("bottomSheetBehavior"), mockBottomSheetBehavior)

        andesBottomSheet.peekHeight = newPeekHeight

        verify(mockBottomSheetBehavior).setPeekHeight(newPeekHeight)
    }

    private fun mockFragmentManager(): FragmentManager {
        val fragmentTransaction = mock(FragmentTransaction::class.java)
        `when`(fragmentTransaction.replace(anyOrNull(), any(Fragment::class.java))).thenReturn(fragmentTransaction)
        `when`(fragmentTransaction.commit()).thenReturn(0)

        val fragmentManager = mock(FragmentManager::class.java)
        `when`(fragmentManager.beginTransaction()).thenReturn(fragmentTransaction)

        return fragmentManager
    }

    companion object {
        private const val DEFAULT_PEEK_HEIGHT = 0
        private val DEFAULT_BOTTOM_SHEET_STATE = AndesBottomSheetState.COLLAPSED
    }
}
