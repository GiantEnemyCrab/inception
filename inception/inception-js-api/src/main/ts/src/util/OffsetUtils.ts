/*
 * Licensed to the Technische Universität Darmstadt under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The Technische Universität Darmstadt
 * licenses this file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

export function calculateStartOffset (root: Node, element : Node) : number {
  try {
    const range = document.createRange()
    range.setStart(root, 0)
    range.setEnd(element, 0)
    return range.toString().length
  } catch (error) {
    console.error('Unable to determine start offset of element:', element, error)
    return -1
  }
}

export function calculateEndOffset (root: Node, element : Node) : number {
  try {
    const range = document.createRange()
    range.selectNodeContents(element)
    range.setStart(root, 0)
    return range.toString().length
  } catch (error) {
    console.error('Unable to determine end offset of element:', element, error)
    return -1
  }
}

export function offsetToRange (root: Element, begin: number, end: number): Range | null {
  let base = 0

  const ni = document.createNodeIterator(root, NodeFilter.SHOW_TEXT)
  let n = ni.nextNode()

  // Seek start node
  while (n != null && base + n.textContent.length <= begin) {
    base += n.textContent.length
    n = ni.nextNode()
  }

  if (n == null) {
    // Did not find the begin offset....
    return null
  }

  const startNode = n
  const startOffset = begin - base

  while (n != null && base + n.textContent.length <= end) {
    base += n.textContent.length
    n = ni.nextNode()
  }

  if (n == null) {
    // Did not find the end offset....
    return null
  }

  const endNode = n
  const endOffset = end - base

  const range = document.createRange()
  range.setStart(startNode, startOffset)
  range.setEnd(endNode, endOffset)
  return range
}