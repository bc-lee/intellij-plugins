// Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package org.jetbrains.vuejs.lang.html

import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.ResolveState
import com.intellij.psi.impl.source.html.HtmlFileImpl
import com.intellij.psi.scope.PsiScopeProcessor
import com.intellij.psi.util.elementType
import com.intellij.psi.util.parentOfType
import com.intellij.psi.util.parents
import com.intellij.psi.xml.XmlTag
import org.jetbrains.vuejs.index.findModule
import org.jetbrains.vuejs.index.isScriptSetupTag
import org.jetbrains.vuejs.lang.LangMode
import org.jetbrains.vuejs.lang.html.lexer.VueLangModeMarkerElementType
import org.jetbrains.vuejs.lang.html.stub.impl.VueFileStubImpl

class VueFile(viewProvider: FileViewProvider) : HtmlFileImpl(viewProvider, VueFileElementType.INSTANCE) {
  override fun getStub(): VueFileStubImpl? = super.getStub() as VueFileStubImpl?

  val langMode: LangMode
    get() {
      val stub = stub
      if (stub != null) {
        return stub.langMode
      }

      val astMarker = lastChild?.elementType
      return if (astMarker is VueLangModeMarkerElementType) astMarker.langMode else LangMode.DEFAULT
    }

  override fun processDeclarations(processor: PsiScopeProcessor, state: ResolveState, lastParent: PsiElement?, place: PsiElement): Boolean {
    if (place.parentOfType<XmlTag>()?.isScriptSetupTag() == true) {
      findModule(this, false)
        ?.takeIf { module -> place.parents(true).none { it == module } }
        ?.processDeclarations(processor, state, lastParent, place)
        ?.let {
          if (!it) return false
        }
    }
    return super.processDeclarations(processor, state, lastParent, place)
  }
}