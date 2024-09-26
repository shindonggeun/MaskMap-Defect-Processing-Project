// components/Todo/TodoList.tsx

import React from 'react';
import TodoItem from './TodoItem';
import { TodoType } from '../../types/TodoType'; // TodoType 불러오기

type TodoListProps = {
  todos: TodoType[];         // 할 일 목록 배열
  onToggle: (id: number) => void;   // 완료 상태 변경 함수
  onDelete: (id: number) => void;   // 삭제 함수
}

const TodoList: React.FC<TodoListProps> = ({ todos, onToggle, onDelete }) => {
  return (
    <div>
      {/* 할 일 목록이 비어 있는지 확인하여 조건부 렌더링 */}
      {todos.length === 0 ? (
        <p>할일 목록이 없습니다.</p>  // 할 일이 없을 때 보여줄 메시지
      ) : (
        todos.map((todo) => (
          <TodoItem
            key={todo.id}                // 고유 ID를 key로 전달
            task={todo.task}             // 할 일 내용
            completed={todo.completed}   // 완료 상태
            onToggle={() => onToggle(todo.id)}   // 완료 상태 변경 함수
            onDelete={() => onDelete(todo.id)}   // 삭제 함수
          />
        ))
      )}
    </div>
  );
};

export default TodoList;